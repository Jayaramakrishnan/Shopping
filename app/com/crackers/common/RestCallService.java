package com.crackers.common;

import org.apache.log4j.Logger;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;

import com.crackers.exceptions.AccessDeninedException;
import com.crackers.vo.WSError;
import com.fasterxml.jackson.databind.JsonNode;

public class RestCallService
{

	private static Logger		logger			= Logger.getLogger(RestCallService.class);
	public static final Integer	OK				= 200;
	public static final Integer	NO_CONTENT		= 204;
	public static final Integer	UNAUTHORIZED	= 401;
	public static final Integer	BAD_REQUEST		= 400;

	public static JsonNode callGetRestService(WSRequestHolder requestHolder)
	{
		Promise<JsonNode> entity = requestHolder.get().map(new Function<WSResponse, JsonNode>() {

			public JsonNode apply(WSResponse response) throws AccessDeninedException
			{
				CMSLogger.info(Logger.getLogger(RestHelper.class), "response uri: " + response.getUri());
				CMSLogger.info(Logger.getLogger(RestHelper.class), "response status: " + response.getStatus());
				if (response.getStatus() == OK)
				{
					return response.asJson();
				}
				else if (response.getStatus() == NO_CONTENT)
				{
					return null;
				}
				else if (response.getStatus() == UNAUTHORIZED)
				{
					WSError wsError = new WSError();
					wsError.setIdWSError(UNAUTHORIZED);
					wsError.setWsError("Unauthorized");
					return Json.toJson(wsError);
				}
				else if (response.getStatus() == BAD_REQUEST)
				{
					CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
					WSError wsError = new WSError();
					wsError.setIdWSError(response.getStatus());
					wsError.setWsError(response.getBody());
					return Json.toJson(wsError);
				}
				else
				{
					CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
					WSError wsError = new WSError();
					wsError.setIdWSError(response.getStatus());
					wsError.setWsError("Un expected error");
					return Json.toJson(wsError);
				}
			}
		});
		return entity.get(RestUrlAttribute.REST_WAIT_TIME);
	}

	public static JsonNode callPostRestService(WSRequestHolder requestHolder, Object object)
	{
		CMSLogger.info(logger, "Creating class " + object.getClass().getName());
		JsonNode jsonNode = Json.toJson(object);
		Promise<JsonNode> entity = requestHolder.post(jsonNode).map(new Function<WSResponse, JsonNode>() {

			public JsonNode apply(WSResponse response) throws AccessDeninedException
			{
				CMSLogger.info(Logger.getLogger(RestHelper.class), "response uri: " + response.getUri());
				CMSLogger.info(Logger.getLogger(RestHelper.class), "response status: " + response.getStatus());
				if (response.getStatus() == OK)
				{
					return response.asJson();
				}
				else if (response.getStatus() == NO_CONTENT)
				{
					return null;
				}
				else if (response.getStatus() == UNAUTHORIZED)
				{
					WSError wsError = new WSError();
					wsError.setIdWSError(UNAUTHORIZED);
					wsError.setWsError("Unauthorized");
					return Json.toJson(wsError);
				}
				else
				{
					WSError wsError = new WSError();
					wsError.setIdWSError(response.getStatus());
					wsError.setWsError(response.getBody());
					CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
					return Json.toJson(wsError);
				}
			}
		});
		return entity.get(RestUrlAttribute.REST_WAIT_TIME);
	}

	public static JsonNode callPutRestService(WSRequestHolder requestHolder, Object object)
	{
		CMSLogger.info(logger, "Updating class " + object.getClass().getName());
		JsonNode jsonNode = Json.toJson(object);
		Promise<JsonNode> entity = requestHolder.put(jsonNode).map(new Function<WSResponse, JsonNode>() {

			public JsonNode apply(WSResponse response) throws AccessDeninedException
			{
				CMSLogger.info(Logger.getLogger(RestHelper.class), "response uri: " + response.getUri());
				CMSLogger.info(Logger.getLogger(RestHelper.class), "response status: " + response.getStatus());

				if (response.getStatus() == OK)
				{
					return response.asJson();
				}
				else if (response.getStatus() == NO_CONTENT)
				{
					return null;
				}
				else
				{
					WSError wsError = new WSError();
					wsError.setIdWSError(response.getStatus());
					wsError.setWsError(response.getBody());
					CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
					return Json.toJson(wsError);
				}
			}
		});
		return entity.get(RestUrlAttribute.REST_WAIT_TIME);
	}
}