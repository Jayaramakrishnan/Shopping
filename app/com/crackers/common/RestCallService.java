package com.crackers.common;

import org.apache.log4j.Logger;

import com.crackers.vo.WSError;
import com.fasterxml.jackson.databind.JsonNode;

import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;

public class RestCallService
{

    private static Logger       logger       = Logger.getLogger(RestCallService.class);
    public static final Integer OK           = 200;
    public static final Integer NO_CONTENT   = 204;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer BAD_REQUEST  = 400;

    public static JsonNode callGetRestService(WSRequestHolder requestHolder)
    {
        Promise<JsonNode> entity = requestHolder.get().map(response -> {
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
                CrackersLogger.error(logger, response.getBody(), null);
                WSError wsError = new WSError();
                wsError.setIdWSError(response.getStatus());
                wsError.setWsError(response.getBody());
                return Json.toJson(wsError);
            }
            else
            {
                CrackersLogger.error(logger, response.getBody(), null);
                WSError wsError = new WSError();
                wsError.setIdWSError(response.getStatus());
                wsError.setWsError("Un expected error");
                return Json.toJson(wsError);
            }
        });
        return entity.get(RestUrlAttribute.REST_WAIT_TIME);
    }

    public static JsonNode callPostRestService(WSRequestHolder requestHolder, Object object)
    {
        CrackersLogger.info(logger, "Creating class " + object.getClass().getName());
        JsonNode jsonNode = Json.toJson(object);
        Promise<JsonNode> entity = requestHolder.post(jsonNode).map(response -> {
            if (response.getStatus() == OK)
            {
                return response.asJson();
            }
            else if (response.getStatus() == NO_CONTENT)
            {
                WSError wsError = new WSError();
                wsError.setIdWSError(NO_CONTENT);
                wsError.setWsError("No Content");
                return Json.toJson(wsError);
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
                CrackersLogger.error(logger, response.getBody(), null);
                return Json.toJson(wsError);
            }
        });
        return entity.get(RestUrlAttribute.REST_WAIT_TIME);
    }

    public static JsonNode callPutRestService(WSRequestHolder requestHolder, Object object)
    {
        CrackersLogger.info(logger, "Updating class " + object.getClass().getName());
        JsonNode jsonNode = Json.toJson(object);
        Promise<JsonNode> entity = requestHolder.put(jsonNode).map(response -> {
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
                CrackersLogger.error(logger, response.getBody(), null);
                return Json.toJson(wsError);
            }
        });
        return entity.get(RestUrlAttribute.REST_WAIT_TIME);
    }
}