package com.crackers.controllers;

import java.util.Date;

import org.apache.log4j.Logger;

import com.crackers.common.CommonConstants;
import com.crackers.common.CrackersLogger;
import com.crackers.common.RestHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.AccessDeninedException;
import com.crackers.interceptors.SessionHandler;
import com.crackers.model.Role;
import com.crackers.util.CacheManager;
import com.fasterxml.jackson.databind.JsonNode;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import play.Play;
import play.cache.Cache;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

@SessionHandler
public class BaseController extends Controller {

	private static Logger logger = Logger.getLogger(BaseController.class);

	public Result invokeLogin(String email, String password) {
		CrackersLogger.info(logger, "For authentication");
		String url = RestUrlAttribute.REST_BASE_URL + Play.application().configuration().getString(RestUrlAttribute.AUTHENTICATE);
		CrackersLogger.info(logger, "Invoking the url: " + url);
		WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(url);
		CrackersLogger.info(logger, "request" + request());
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
		requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
		CrackersLogger.info(logger, "CommonConstants.CLIENT_IP_STRING" + CommonConstants.CLIENT_IP_STRING);
		if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null) {
			requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
			requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
		}
		requestHolder.setHeader("username", email).setHeader("password", password);
		Promise<String> uniqueId = requestHolder.get().map(new Function<WSResponse, String>() {

			public String apply(WSResponse response) throws AccessDeninedException {
				CrackersLogger.info(logger, "response status: " + response.getUri());
				CrackersLogger.info(logger, "response status: " + response.getStatus());
				if (response.getStatus() == OK) {
					return response.getBody();
				}
				else {
					return "UNAUTHORIZED";
				}
			}
		});
		String id = uniqueId.get(RestUrlAttribute.REST_WAIT_TIME);
		if (id == null || id.equals("UNAUTHORIZED")) {
			// return ok(index.render("Invalid username/password.", Dashboard.clientConfigurationSettings, ""));
			return ok();
		}
		id = id.substring(1, id.length() - 1);
		long currentT = new Date().getTime();
		String previousT = String.valueOf(currentT);
		session("usertime", previousT);
		CrackersLogger.info(logger, "id :" + id);
		session().put(CommonConstants.UNIQUE_ID, id);
		url = RestUrlAttribute.REST_BASE_URL + Play.application().configuration().getString(RestUrlAttribute.USER_INFO);
		CrackersLogger.info(logger, "Invoking the url: " + url);
		requestHolder = RestHelper.checkProxyAndSetHeader(url);
		requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
		Promise<JsonNode> userInfo = requestHolder.get().map(new Function<WSResponse, JsonNode>() {

			public JsonNode apply(WSResponse response) throws AccessDeninedException {
				CrackersLogger.info(logger, "response status: " + response.getUri());
				CrackersLogger.info(logger, "response status: " + response.getStatus());
				if (response.getStatus() == OK) {
					return Json.parse(response.getBody());
				}
				else {
					CrackersLogger.info(logger, "");
					return Json.parse("UNAUTHORIZED");
				}
			}
		});
		JsonNode userInfoNode = userInfo.get(RestUrlAttribute.REST_WAIT_TIME);
		CrackersLogger.info(logger, "User information has been retrieved");
		if (userInfoNode.isObject()) {
			UserDto successDto = Json.fromJson(userInfoNode, UserDto.class);
			String unique = session().get(CommonConstants.UNIQUE_ID);
			CrackersLogger.info(logger, "User id is " + successDto.getId());
			session().put(unique, successDto.getId().toString());
			if (successDto.getUserName() != null) {
				session().put(CommonConstants.USER_NAME, successDto.getUserName());
			}
			Role userRole = CacheManager.getUserRoleFromCache(id);
			session().put(CommonConstants.USER_ROLE_ID, userRole.getId().toString());
			Cache.set(session().get(CommonConstants.UNIQUE_ID), successDto);
		}
		return redirect("/DashBoard");
	}
}