package com.crackers.controllers.web;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import play.Play;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import views.html.failure;
import views.html.index;

import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.common.RestCallService;
import com.crackers.common.RestHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.controllers.BaseController;
import com.crackers.controllers.Dashboard;
import com.crackers.dto.PasswordDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.AccessDeninedException;
import com.crackers.model.Password;
import com.crackers.model.Role;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.services.UserService;
import com.crackers.util.CacheManager;
import com.crackers.util.CryptoBinderUtil;
import com.crackers.vo.WSError;
import com.fasterxml.jackson.databind.JsonNode;

import views.html.createPassword;
import views.html.forgotPassword;
import views.html.linkExpired;
import views.html.mobileCreatePassword;
import views.html.mobileForgotPassword;
import views.html.mobileResetPasswordMessage;
import views.html.resetPasswordMessage;

@Component
public class PasswordController extends BaseController
{

    private static Logger               logger = Logger.getLogger(PasswordController.class);
    @Resource
    private UserService                 userService;
    @Resource
    private ApplicationConfigRepository applicationConfigRepository;

    public synchronized Result createPassword(Integer idPassword, String email)
    {
        CMSLogger.info(logger, "Password creation for new user" + idPassword + "email" + email);
        if (!(session().isEmpty()) && session(CommonConstants.UNIQUE_ID) != null && session().get(session().get(CommonConstants.UNIQUE_ID)) != null)
        {
            session().clear();
        }
        try
        {
            if (email != null && !email.equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES))
            {
                // service call to check the emailid of the user
                String baseUrl = RestUrlAttribute.REST_BASE_URL;
                Object object = null;
                String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.NEW_USER_VALIDATE_PASSWORD));
                PasswordDto passwordDto = new PasswordDto();
                passwordDto.setIdPassword(idPassword);
                passwordDto.setEncryptText(email);
                object = passwordDto;
                WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
                requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
                if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
                {
                    UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                    ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
                    requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
                    requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
                }
                JsonNode entityNode = RestCallService.callPostRestService(requestHolder, object);
                CMSLogger.info(logger, "entityNode" + entityNode);
                if (entityNode.isObject())
                {
                    WSError wsError = Json.fromJson(entityNode, WSError.class);
                    if (wsError.getWsError().equals("Unauthorized"))
                    {
                        // return ok(failure.render("Sorry, please contact administrator.", Dashboard.clientConfigurationSettings));
                        return ok();
                    }
                    else if (wsError.getIdWSError().equals(BAD_REQUEST) && wsError.getWsError() != null && !wsError.getWsError().isEmpty())
                    {
                        // return ok(failure.render(wsError.getWsError(), Dashboard.clientConfigurationSettings));
                        return ok();
                    }
                }
                // return ok(createPassword.render("Enter a password below.", "create a password", email, idPassword, "createNewPasswordWeb", "", Dashboard.clientConfigurationSettings));
                return ok();
            }
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Error while redirecting the user for creating the password", exception);
        }
        // return ok(failure.render("Sorry, please contact administrator.", Dashboard.clientConfigurationSettings));
        return ok();
    }

    public synchronized Result validateEmailForForgotPassword()
    {
        CMSLogger.info(logger, "validateEmailForForgotPassword - password reset");
        DynamicForm requestData = Form.form().bindFromRequest();
        String email = requestData.get("email");
        if (email == null || email.equals(RestUrlAttribute.EMPTY_QUOTES))
        {
            CMSLogger.info(logger, "Returning BadRequest from validateEmailForForgotPassword");
            return badRequest();
        }
        String support = applicationConfigRepository.getConfigValueByKey(CommonConstants.SUPPORT_EMAIL_ID);
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
        try
        {
            CMSLogger.info(logger, "email " + email);
            // service call to check the emailid of the user
            String baseUrl = RestUrlAttribute.REST_BASE_URL;
            String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.FORGOT_USER_VALIDATE_EMAIL));
            CMSLogger.info(logger, "Service +" + serviceUrl);
            WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
            requestHolder.setQueryParameter("email", email);
            requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
            CMSLogger.info(logger, "CommonConstants.CLIENT_IP_STRING" + CommonConstants.CLIENT_IP_STRING);
            if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
            {
                requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
                requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
            }
            JsonNode entityNode = RestCallService.callGetRestService(requestHolder);
            CMSLogger.info(logger, "entityNode" + entityNode);
            if (entityNode.isObject())
            {
                WSError wsError = Json.fromJson(entityNode, WSError.class);
                if (wsError.getWsError().equals("Unauthorized"))
                {
                    // return ok(failure.render("Sorry, please contact administrator.", Dashboard.clientConfigurationSettings));
                    return ok();
                }
                else if (wsError.getIdWSError().equals(BAD_REQUEST) && wsError.getWsError() != null && !wsError.getWsError().isEmpty())
                {
                    // return ok(failure.render(wsError.getWsError(), Dashboard.clientConfigurationSettings));
                    return ok();
                }
            }
            if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
            {
                // return ok(mobileResetPasswordMessage.render(email, Dashboard.clientConfigurationSettings));
                return ok();
            }
            // return ok(resetPasswordMessage.render(email, support, Dashboard.clientConfigurationSettings));
            return ok();
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Error while redirecting the user for creating the password", exception);
        }
        if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
        {
            // return ok(mobileResetPasswordMessage.render(email, Dashboard.clientConfigurationSettings));
            return ok();
        }
        // return ok(resetPasswordMessage.render(email, support, Dashboard.clientConfigurationSettings));
        return ok();
    }

    public synchronized Result forgotPassword(Integer idPassword, String email, Integer errorType)
    {
        CMSLogger.info(logger, "Enter a password below.reset password" + email + idPassword + "createForgetPasswordWeb");
        if (errorType.intValue() == 1)
        {
            // return ok(createPassword.render("Enter a password below.", "reset password", email, idPassword, "createForgetPasswordWeb", "Previous passwords are equal", Dashboard.clientConfigurationSettings));
            return ok();
        }
        CMSLogger.info(logger, "Forgot password - password reset");
        if (!(session().isEmpty()) && session(CommonConstants.UNIQUE_ID) != null && session().get(session().get(CommonConstants.UNIQUE_ID)) != null)
        {
            session().clear();
        }
        try
        {
            if (email != null && !email.equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES))
            {
                // service call to check the emailid of the user
                String baseUrl = RestUrlAttribute.REST_BASE_URL;
                Object object = null;
                String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.NEW_USER_VALIDATE_PASSWORD));
                PasswordDto passwordDto = new PasswordDto();
                passwordDto.setIdPassword(idPassword);
                passwordDto.setEncryptText(email);
                object = passwordDto;
                WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
                JsonNode entityNode = RestCallService.callPostRestService(requestHolder, object);
                CMSLogger.info(logger, "entityNode" + entityNode);
                CMSLogger.info(logger, "entityNode" + entityNode);
                requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
                CMSLogger.info(logger, "CommonConstants.CLIENT_IP_STRING" + CommonConstants.CLIENT_IP_STRING);
                if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
                {
                    UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                    ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
                    requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
                    requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
                }
                if (entityNode.isObject())
                {
                    WSError wsError = Json.fromJson(entityNode, WSError.class);
                    if (wsError.getWsError().equals("Unauthorized"))
                    {
                        Password password = userService.getPasswordExpiredObject(passwordDto.getIdPassword());
                        if (password != null)
                        {
                            String decryptText = CryptoBinderUtil.getDecryptedString(passwordDto.getEncryptText());
                            CMSLogger.info(logger, "decryptText" + decryptText);
                            if (decryptText.equals(password.getEmail()))
                            {
                                // return ok(linkExpired.render(decryptText, Dashboard.clientConfigurationSettings));
                                return ok();
                            }
                        }
                    }
                    else if (wsError.getIdWSError().equals(BAD_REQUEST) && wsError.getWsError() != null && !wsError.getWsError().isEmpty())
                    {
                        // return ok(failure.render(wsError.getWsError(), Dashboard.clientConfigurationSettings));
                        return ok();
                    }
                }
                // return ok(createPassword.render("Enter a password below.", "reset password", email, idPassword, "createForgetPasswordWeb", "", Dashboard.clientConfigurationSettings));
                return ok();
            }
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Error while redirecting the user for creating the password", exception);
        }
        // return ok(failure.render("Sorry, please contact administrator.", Dashboard.clientConfigurationSettings));
        return ok();
    }

    public synchronized Result resetPassword(Integer idPassword, String email)
    {
        CMSLogger.info(logger, "Reset password - password reset");
        try
        {
            if (email != null && !email.equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES))
            {
                // service call to check the emailid of the user
                String baseUrl = RestUrlAttribute.REST_BASE_URL;
                Object object = null;
                String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.NEW_USER_VALIDATE_PASSWORD));
                PasswordDto passwordDto = new PasswordDto();
                passwordDto.setIdPassword(idPassword);
                passwordDto.setEncryptText(email);
                object = passwordDto;
                WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
                JsonNode entityNode = RestCallService.callPostRestService(requestHolder, object);
                CMSLogger.info(logger, "entityNode" + entityNode);
                UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
                requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
                CMSLogger.info(logger, "CommonConstants.CLIENT_IP_STRING" + CommonConstants.CLIENT_IP_STRING);
                if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
                {
                    requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
                    requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
                }
                if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
                {
                    // return ok(mobileCreatePassword.render("Enter a password below.", "reset password", email, idPassword, "createResetPasswordWeb", Dashboard.clientConfigurationSettings));
                    return ok();
                }
                // return ok(createPassword.render("Enter a password below.", "reset password", email, idPassword, "createResetPasswordWeb", "", Dashboard.clientConfigurationSettings));
                return ok();
            }
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Error while redirecting the user for creating the password", exception);
        }
        // return ok(failure.render("Sorry, please contact administrator.", Dashboard.clientConfigurationSettings));
        return ok();
    }

    public synchronized Result createNewLoginPassword()
    {
        try
        {
            String baseUrl = RestUrlAttribute.REST_BASE_URL;
            Object object = null;
            DynamicForm requestData = Form.form().bindFromRequest();
            if (requestData.get("idPassword") == null || requestData.get("password") == null || requestData.get("email") == null)
            {
                return badRequest();
            }
            Integer idPassword = Integer.parseInt(requestData.get("idPassword"));
            String password = requestData.get("password");
            String email = requestData.get("email");
            Password passwordObj = userService.getPasswordObject(idPassword);
            String decryptText = CryptoBinderUtil.getDecryptedString(email);
            String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.CREATE_NEW_LOGIN_PASSWORD));
            UserDto userDto = new UserDto();
            userDto.setNewPassword(password);
            userDto.setEmail(decryptText);
            object = userDto;
            WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
            JsonNode entityNode = RestCallService.callPostRestService(requestHolder, object);
            if (entityNode != null && entityNode.isObject())
            {
                String url = RestUrlAttribute.REST_BASE_URL + Play.application().configuration().getString(RestUrlAttribute.AUTHENTICATE);
                CMSLogger.info(logger, "Invoking the url: " + url);
                requestHolder = RestHelper.checkProxyAndSetHeader(url);
                requestHolder.setHeader("username", decryptText).setHeader("password", password);
                UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
                requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
                CMSLogger.info(logger, "CommonConstants.CLIENT_IP_STRING" + CommonConstants.CLIENT_IP_STRING);
                if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
                {
                    requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
                    requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
                }
                Promise<String> uniqueId = requestHolder.get().map(new Function<WSResponse, String>() {

                    public String apply(WSResponse response) throws AccessDeninedException
                    {
                        CMSLogger.info(logger, "response status: " + response.getUri());
                        CMSLogger.info(logger, "response status: " + response.getStatus());
                        if (response.getStatus() == OK)
                        {
                            return response.getBody();
                        }
                        else
                        {
                            return "UNAUTHORIZED";
                        }
                    }
                });
                String id = uniqueId.get(RestUrlAttribute.REST_WAIT_TIME);
                if (id == null || id.equals("UNAUTHORIZED"))
                {
                    // return ok(index.render("Invalid username/password.", Dashboard.clientConfigurationSettings, ""));
                    return ok();
                }
                id = id.substring(1, id.length() - 1);
                long currentT = new Date().getTime();
                String previousT = String.valueOf(currentT);
                session("usertime", previousT);
                CMSLogger.info(logger, "id :" + id);
                session().put(CommonConstants.UNIQUE_ID, id);
                url = RestUrlAttribute.REST_BASE_URL + Play.application().configuration().getString(RestUrlAttribute.USER_INFO);
                CMSLogger.info(logger, "Invoking the url: " + url);
                requestHolder = RestHelper.checkProxyAndSetHeader(url);
                requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
                Promise<JsonNode> userInfo = requestHolder.get().map(new Function<WSResponse, JsonNode>() {

                    public JsonNode apply(WSResponse response) throws AccessDeninedException
                    {
                        CMSLogger.info(logger, "response status: " + response.getUri());
                        CMSLogger.info(logger, "response status: " + response.getStatus());
                        if (response.getStatus() == OK)
                        {
                            return Json.parse(response.getBody());
                        }
                        else
                        {
                            CMSLogger.info(logger, "");
                            return Json.parse("UNAUTHORIZED");
                        }
                    }
                });
                JsonNode userInfoNode = userInfo.get(RestUrlAttribute.REST_WAIT_TIME);
                CMSLogger.info(logger, "User information has been retrieved");
                if (userInfoNode.isObject())
                {
                    UserDto successDto = Json.fromJson(userInfoNode, UserDto.class);
                    CMSLogger.info(logger, "User id is " + successDto.getIdUser());
                    session().put(session().get(CommonConstants.UNIQUE_ID), successDto.getIdUser().toString());
                    if (successDto.getUserName() != null)
                    {
                        session().put(CommonConstants.USER_NAME, successDto.getUserName());
                    }
                    Role userRole = CacheManager.getUserRoleFromCache(id);
                    session().put(CommonConstants.USER_ROLE_ID, userRole.getIdRole().toString());
                    Cache.set(session().get(CommonConstants.UNIQUE_ID), successDto);
                }
                return redirect("/DashBoard");
            }
            else
            {
                return noContent();
            }
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Exception", e);
            return internalServerError();
        }
    }

    public synchronized Result createForgetLoginPassword()
    {
        try
        {
            String baseUrl = RestUrlAttribute.REST_BASE_URL;
            Object object = null;
            DynamicForm requestData = Form.form().bindFromRequest();
            if (requestData.get("idPassword") == null || requestData.get("password") == null || requestData.get("email") == null)
            {
                return badRequest();
            }
            Integer idPassword = Integer.parseInt(requestData.get("idPassword"));
            String password = requestData.get("password");
            String email = requestData.get("email");
            Password passwordObj = userService.getPasswordObject(idPassword);
            String decryptText = CryptoBinderUtil.getDecryptedString(email);
            String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.CREATE_FORGET_PASSWORD));
            UserDto userDto = new UserDto();
            userDto.setNewPassword(password);
            userDto.setEmail(decryptText);
            object = userDto;
            WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
            requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
            JsonNode entityNode = callPostRestLocalServiceForgot(requestHolder, object);
            if (entityNode != null && entityNode.isObject())
            {
                WSError wsError = Json.fromJson(entityNode, WSError.class);
                CMSLogger.info(logger, "Password resetted, now going for authentication");
                if (wsError.getWsError().equals("ok"))
                {
                    String url = RestUrlAttribute.REST_BASE_URL + Play.application().configuration().getString(RestUrlAttribute.AUTHENTICATE);
                    CMSLogger.info(logger, "Invoking the url: " + url);
                    requestHolder = RestHelper.checkProxyAndSetHeader(url);
                    CMSLogger.info(logger, "request" + request());
                    UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                    ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
                    requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
                    CMSLogger.info(logger, "CommonConstants.CLIENT_IP_STRING" + CommonConstants.CLIENT_IP_STRING);
                    if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
                    {
                        requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
                        requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
                    }
                    requestHolder.setHeader("username", decryptText).setHeader("password", password);
                    Promise<String> uniqueId = requestHolder.get().map(new Function<WSResponse, String>() {

                        public String apply(WSResponse response) throws AccessDeninedException
                        {
                            CMSLogger.info(logger, "response status: " + response.getUri());
                            CMSLogger.info(logger, "response status: " + response.getStatus());
                            if (response.getStatus() == OK)
                            {
                                return response.getBody();
                            }
                            else
                            {
                                return "UNAUTHORIZED";
                            }
                        }
                    });
                    String id = uniqueId.get(RestUrlAttribute.REST_WAIT_TIME);
                    if (id == null || id.equals("UNAUTHORIZED"))
                    {
                        // return ok(index.render("Invalid username/password.", Dashboard.clientConfigurationSettings, ""));
                        return ok();
                    }
                    id = id.substring(1, id.length() - 1);
                    long currentT = new Date().getTime();
                    String previousT = String.valueOf(currentT);
                    session("usertime", previousT);
                    CMSLogger.info(logger, "id :" + id);
                    session().put(CommonConstants.UNIQUE_ID, id);
                    url = RestUrlAttribute.REST_BASE_URL + Play.application().configuration().getString(RestUrlAttribute.USER_INFO);
                    CMSLogger.info(logger, "Invoking the url: " + url);
                    requestHolder = RestHelper.checkProxyAndSetHeader(url);
                    requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
                    Promise<JsonNode> userInfo = requestHolder.get().map(new Function<WSResponse, JsonNode>() {

                        public JsonNode apply(WSResponse response) throws AccessDeninedException
                        {
                            CMSLogger.info(logger, "response status: " + response.getUri());
                            CMSLogger.info(logger, "response status: " + response.getStatus());
                            if (response.getStatus() == OK)
                            {
                                return Json.parse(response.getBody());
                            }
                            else
                            {
                                CMSLogger.info(logger, "");
                                return Json.parse("UNAUTHORIZED");
                            }
                        }
                    });
                    JsonNode userInfoNode = userInfo.get(RestUrlAttribute.REST_WAIT_TIME);
                    CMSLogger.info(logger, "User information has been retrieved");
                    if (userInfoNode.isObject())
                    {
                        UserDto successDto = Json.fromJson(userInfoNode, UserDto.class);
                        String unique = session().get(CommonConstants.UNIQUE_ID);
                        CMSLogger.info(logger, "User id is " + successDto.getIdUser());
                        session().put(unique, successDto.getIdUser().toString());
                        if (successDto.getUserName() != null)
                        {
                            session().put(CommonConstants.USER_NAME, successDto.getUserName());
                        }
                        Role userRole = CacheManager.getUserRoleFromCache(id);
                        session().put(CommonConstants.USER_ROLE_ID, userRole.getIdRole().toString());
                        Cache.set(session().get(CommonConstants.UNIQUE_ID), successDto);
                    }
                    return redirect("/DashBoard");
                }
                else if (wsError.getIdWSError().equals(BAD_REQUEST) && wsError.getWsError() != null && !wsError.getWsError().isEmpty())
                {
                    CMSLogger.error(logger, "Previous passwords are equal+" + email, null);
                    return redirect("/forgetPassword/" + idPassword + "/" + email + "?errorType=" + 1);
                }
                else
                {
                    CMSLogger.error(logger, "Sorry, please contact administrator.", null);
                    // return ok(failure.render("Sorry, please contact administrator.", Dashboard.clientConfigurationSettings));
                    return ok();
                }
            }
            else
            {
                CMSLogger.info(logger, "Empty");
                return noContent();
            }
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Exception", e);
            return internalServerError();
        }
    }

    private static JsonNode callPostRestLocalService(WSRequestHolder requestHolder, Object object)
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
                    return null;
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
                    JsonNode jsonNode = Json.toJson(wsError);
                    return jsonNode;
                }
                else if (response.getStatus() == BAD_REQUEST)
                {
                    WSError wsError = new WSError();
                    wsError.setIdWSError(response.getStatus());
                    wsError.setWsError(response.getBody());
                    JsonNode jsonNode = Json.toJson(wsError);
                    CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
                    return jsonNode;
                }
                else
                {
                    CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
                    return Json.parse("{\"Error\" : \"Error in POST Service\"}");
                }
            }
        });
        return entity.get(RestUrlAttribute.REST_WAIT_TIME);
    }

    private static JsonNode callPostRestLocalServiceForgot(WSRequestHolder requestHolder, Object object)
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
                    WSError wsError = new WSError();
                    wsError.setIdWSError(OK);
                    wsError.setWsError("ok");
                    JsonNode jsonNode = Json.toJson(wsError);
                    return jsonNode;
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
                    JsonNode jsonNode = Json.toJson(wsError);
                    return jsonNode;
                }
                else if (response.getStatus() == BAD_REQUEST)
                {
                    WSError wsError = new WSError();
                    wsError.setIdWSError(response.getStatus());
                    wsError.setWsError(response.getBody());
                    JsonNode jsonNode = Json.toJson(wsError);
                    CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
                    return jsonNode;
                }
                else
                {
                    CMSLogger.error(Logger.getLogger(RestHelper.class), response.getBody(), null);
                    return Json.parse("{\"Error\" : \"Error in POST Service\"}");
                }
            }
        });
        return entity.get(RestUrlAttribute.REST_WAIT_TIME);
    }

    public synchronized Result createResetLoginPassword()
    {
        try
        {
            String baseUrl = RestUrlAttribute.REST_BASE_URL;
            Object object = null;
            DynamicForm requestData = Form.form().bindFromRequest();
            if (requestData.get("oldPassword") == null || requestData.get("newPassword") == null)
            {
                return badRequest();
            }
            String newPassword = requestData.get("newPassword");
            String oldPassword = requestData.get("oldPassword");
            String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.CREATE_RESET_PASSWORD));
            UserDto userDto = new UserDto();
            userDto.setNewPassword(newPassword);
            userDto.setOldPassword(oldPassword);
            object = userDto;
            WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
            requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
            JsonNode entityNode = callPostRestLocalService(requestHolder, object);
            if (entityNode != null && entityNode.isObject())
            {
                /*
                 * WSError wsError = Json.fromJson(entityNode, WSError.class); if (wsError.getWsError().equals("ok")) { Object objectWsObject = Json.fromJson(entityNode, Object.class); return ok(Json.toJson(objectWsObject)); } else { return unauthorized(); }
                 */
                return ok(Json.toJson(entityNode));
            }
            else if (entityNode == null)
            {
                CMSLogger.error(logger, "Error while reseting the password", null);
                return ok();
            }
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Error while reseting the password ", exception);
            return internalServerError();
        }
        return ok();
    }

    public synchronized Result showForgotPassword()
    {
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
        if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
        {
            // return ok(mobileForgotPassword.render("Enter your email address and we will send you a link to reset your password.", Dashboard.clientConfigurationSettings));
            return ok();
        }
        // return ok(forgotPassword.render("Enter your email address and we will send you a link to reset your password.", Dashboard.clientConfigurationSettings));
        return ok();
    }
}