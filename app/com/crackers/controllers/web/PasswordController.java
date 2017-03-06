package com.crackers.controllers.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CommonConstants;
import com.crackers.common.CrackersLogger;
import com.crackers.common.RestHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.controllers.BaseController;
import com.crackers.dto.UserDto;
import com.crackers.services.UserService;
import com.crackers.vo.WSError;
import com.fasterxml.jackson.databind.JsonNode;

import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;
import play.mvc.Result;

@Component
public class PasswordController extends BaseController {

	private static Logger	logger	= Logger.getLogger(PasswordController.class);
	@Resource
	private UserService		userService;

	public synchronized Result createForgetLoginPassword() {
		try {
			String baseUrl = RestUrlAttribute.REST_BASE_URL;
			Object object = null;
			DynamicForm requestData = Form.form().bindFromRequest();
			if (requestData.get("password") == null || requestData.get("email") == null) {
				return badRequest();
			}
			String password = requestData.get("password");
			String email = requestData.get("email");
			String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.CREATE_FORGET_PASSWORD));
			UserDto userDto = new UserDto();
			userDto.setNewPassword(password);
			userDto.setUserName(email);
			object = userDto;
			WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
			requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
			JsonNode entityNode = callPostRestLocalServiceForgot(requestHolder, object);
			if (entityNode != null && entityNode.isObject()) {
				WSError wsError = Json.fromJson(entityNode, WSError.class);
				if (wsError.getWsError().equals("ok")) {
					return invokeLogin(email, password);
				}
				else {
					CrackersLogger.error(logger, "Sorry, please contact administrator.", null);
					// return ok(failure.render("Sorry, please contact administrator.", Dashboard.clientConfigurationSettings));
					return ok();
				}
			}
			else {
				CrackersLogger.info(logger, "Empty");
				return noContent();
			}
		}
		catch (Exception e) {
			CrackersLogger.error(logger, "Exception", e);
			return internalServerError();
		}
	}

	private static JsonNode callPostRestLocalServiceForgot(WSRequestHolder requestHolder, Object object) {
		CrackersLogger.info(logger, "Creating class " + object.getClass().getName());
		JsonNode jsonNode = Json.toJson(object);
		Promise<JsonNode> entity = requestHolder.post(jsonNode).map((response) -> {
			CrackersLogger.info(Logger.getLogger(RestHelper.class), CommonConstants.RESPONSEURIMSG + response.getUri());
			CrackersLogger.info(Logger.getLogger(RestHelper.class), CommonConstants.RESPONSESTATUSMSG + response.getStatus());
			if (response.getStatus() == OK) {
				WSError wsError = new WSError();
				wsError.setIdWSError(OK);
				wsError.setWsError("ok");
				return Json.toJson(wsError);
			}
			else if (response.getStatus() == NO_CONTENT) {
				return null;
			}
			else if (response.getStatus() == UNAUTHORIZED) {
				WSError wsError = new WSError();
				wsError.setIdWSError(UNAUTHORIZED);
				wsError.setWsError(CommonConstants.UNAUTHORIZEDMSG);
				return Json.toJson(wsError);
			}
			else if (response.getStatus() == BAD_REQUEST) {
				WSError wsError = new WSError();
				wsError.setIdWSError(response.getStatus());
				wsError.setWsError(response.getBody());
				return Json.toJson(wsError);
			}
			else {
				CrackersLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
				return Json.parse("{\"Error\" : \"Error in POST Service\"}");
			}
		});
		return entity.get(RestUrlAttribute.REST_WAIT_TIME);
	}
}