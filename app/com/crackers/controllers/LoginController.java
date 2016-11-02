package com.crackers.controllers;

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

import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.common.RestHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.AccessDeninedException;
import com.crackers.interceptors.SessionHandler;
import com.crackers.model.Role;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.repositories.UserRoleRepository;
import com.crackers.services.LoginTrackService;
import com.crackers.util.CacheManager;
import com.crackers.vo.ClientConfigurationVO;
import com.fasterxml.jackson.databind.JsonNode;

import views.html.index;
import views.html.mobileLoginPage;
import views.html.redirectDashboard;
import views.html.sessioncheck;

@Component
public class LoginController extends BaseController
{

	private static Logger				logger	= Logger.getLogger(LoginController.class);
	@Resource
	private UserRoleRepository			userRoleRepository;
	@Resource
	private LoginTrackService			loginTrackService;
	@Resource
	private ApplicationConfigRepository	applicationConfigRepository;

	public Result index(String url)
	{
		if (!(session().isEmpty()) && session(CommonConstants.UNIQUE_ID) != null && session().get(session().get(CommonConstants.UNIQUE_ID)) != null && CacheManager.getIdUserFromCache(session().get(CommonConstants.UNIQUE_ID)) != null)
		{
			if (request().getQueryString("url") != null && !request().getQueryString("url").equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES))
			{
				CMSLogger.info(logger, "Setting the redirect Url from Query string '" + request().getQueryString("url") + "'");
				session().put(CommonConstants.REDIRECT_URL, request().getQueryString("url"));
			}
			CMSLogger.info(logger, "Session already exists for user: " + session().get(session().get(CommonConstants.UNIQUE_ID)));
			return redirect("/DashBoard");
		}
		session().clear();
		if (Dashboard.clientConfigurationSettings == null)
		{
			loadClientConfiguration();
		}
		if (request() != null && request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
		{
			CMSLogger.info(logger, "userAgent: " + request().getHeader(CommonConstants.USER_AGENT_STRING));
			UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
			ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
			if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
			{
				logger.info("Device:" + agent.getDeviceCategory().getName());
				// return ok(mobileLoginPage.render("", Dashboard.clientConfigurationSettings, url));
				return ok();
			}
		}
		// return ok(index.render("", Dashboard.clientConfigurationSettings, url));
		return ok();
	}

	@SessionHandler
	public Result sessionCheck()
	{
		if (!(session().isEmpty()) && session(CommonConstants.UNIQUE_ID) != null && session().get(session().get(CommonConstants.UNIQUE_ID)) != null)
		{
//			return ok(sessioncheck.render(RestUrlAttribute.EMPTY_QUOTES));
			return ok();
		}
		loginTrackService.loginTrackLogout(request().getHeader("uniqueId"), true);
		CMSLogger.info(logger, "User session has been expired. " + session());
		session().clear();
//		return ok(sessioncheck.render("Your session has been expired."));
		return ok();
	}

	public Result validate()
	{
		String redirectUrl = RestUrlAttribute.EMPTY_QUOTES;
		try
		{
			CMSLogger.info(logger, "Inside Login");
			DynamicForm requestData = Form.form().bindFromRequest();
			redirectUrl = requestData.get("url");
			/**
			 * Check and load client configuration
			 */
			if (Dashboard.clientConfigurationSettings == null)
			{
				loadClientConfiguration();
			}
			String url = RestUrlAttribute.REST_BASE_URL + Play.application().configuration().getString(RestUrlAttribute.AUTHENTICATE);
			CMSLogger.info(logger, "Invoking the url: " + url);
			WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(url);
			requestHolder.setHeader("username", requestData.get("username")).setHeader("password", requestData.get("password"));
			ReadableUserAgent agent = null;
			if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
			{
				UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
				agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
				requestHolder.setHeader(CommonConstants.USER_DEVICE_STRING, agent.getDeviceCategory().getName());
				requestHolder.setHeader(CommonConstants.USER_AGENT_STRING, request().getHeader(CommonConstants.USER_AGENT_STRING));
			}
			requestHolder.setHeader(CommonConstants.CLIENT_IP_STRING, request().remoteAddress());
			Promise<String> uniqueId = requestHolder.get().map(new Function<WSResponse, String>() {

				public String apply(WSResponse response) throws AccessDeninedException
				{
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
				if (agent != null && agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
				{
//					return ok(mobileLoginPage.render("Invalid username/password.", Dashboard.clientConfigurationSettings, redirectUrl));
					return ok();
				}
				else
				{
//					return ok(index.render("Invalid username/password.", Dashboard.clientConfigurationSettings, redirectUrl));
					return ok();
				}
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
				session().put(CommonConstants.USER_NAME, successDto.getFirstName() + " " + successDto.getLastName());
				session().put(CommonConstants.USER_FIRST_NAME, successDto.getFirstName());
				session().put(CommonConstants.USER_LAST_NAME, successDto.getLastName());
				Role userRole = CacheManager.getUserRoleFromCache(id);
				session().put(CommonConstants.USER_ROLE_ID, userRole.getIdRole().toString());
				CMSLogger.debug(logger, "uniqueId:" + id);
				CMSLogger.debug(logger, "***************UserRole*******************");
				CMSLogger.debug(logger, "role" + userRole.getRole());
				CMSLogger.debug(logger, "***************UserRole*******************");
				// session().put("ROLE_"+session().get(CommonConstants.UNIQUE_ID), userRole.getIdRole().toString());//TODO have to remove after completing relationship
				Cache.set(session().get(CommonConstants.UNIQUE_ID), successDto);
			}
			CMSLogger.info(logger, "User is valid, session has been created");
		}
		catch (Exception exception)
		{
			session().clear();
			CMSLogger.error(logger, "Error while validating the user", exception);
			if (request().getHeader(CommonConstants.USER_AGENT_STRING) != null)
			{
				UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
				ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
				if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
				{
//					return ok(mobileLoginPage.render("Invalid username/password.", Dashboard.clientConfigurationSettings, redirectUrl));
					return ok();
				}
			}
//			return ok(index.render("Invalid username/password.", Dashboard.clientConfigurationSettings, redirectUrl));
			return ok();
		}
		if (redirectUrl != null && redirectUrl.length() > 0)
		{
			session().put(CommonConstants.REDIRECT_URL, redirectUrl);
//			return ok(redirectDashboard.render());
			return ok();
		}
		session().put(CommonConstants.REDIRECT_URL, "");
//		return ok(redirectDashboard.render());
		return ok();
	}

	public Result logout()
	{
		CMSLogger.info(logger, "User with session is logging out " + session());
		if (!(session().isEmpty()))
		{
			loginTrackService.loginTrackLogout(session().get(CommonConstants.UNIQUE_ID), false);
			CacheManager.removeAllFromCache(session().get(CommonConstants.UNIQUE_ID));
			session().clear();
			CMSLogger.info(logger, "User with session cleared successfully " + session());
		}
		return redirect("/");
	}

	/**
	 * Loads the client configuration like client code, rest base url, etc.,
	 * 
	 * @param clientConfigurationSettings
	 */
	public Result loadClientConfiguration()
	{
		String[] keys = { "client", "version", "restbase.url", "websocket.url", "rte", "is.websocket.enabled", "sessionTimeout", "APP_START_YEAR", "applicationNameInheader", "applicationNameInLogin", "isApplicationLogoAvailable",
				"applicationTitleName", "date.format", "autoSaveTimeout", "isTimeRequired", "allTheseWords", "isTLSOn", "isSendEmailOn"};
		Dashboard.clientConfigurationSettings = new ClientConfigurationVO();
		try
		{
			if (applicationConfigRepository.getConfigValueByKey("read_from_conf").equalsIgnoreCase("0"))
			{
				CMSLogger.info(logger, "Loading configuration from database");
				Dashboard.clientConfigurationSettings.setClient(applicationConfigRepository.getConfigValueByKey(keys[0]));
				Dashboard.clientConfigurationSettings.setVersion(applicationConfigRepository.getConfigValueByKey(keys[1]));
				Dashboard.clientConfigurationSettings.setRestBaseUrl(applicationConfigRepository.getConfigValueByKey(keys[2]));
				RestUrlAttribute.REST_BASE_URL = applicationConfigRepository.getConfigValueByKey(keys[2]);
				Dashboard.clientConfigurationSettings.setWebsocketUrl(applicationConfigRepository.getConfigValueByKey(keys[3]));
				Dashboard.clientConfigurationSettings.setRte(applicationConfigRepository.getConfigValueByKey(keys[4]));
				Dashboard.clientConfigurationSettings.setIsWebsocketEnabled(applicationConfigRepository.getConfigValueByKey(keys[5]));
				Dashboard.clientConfigurationSettings.setSessionTimeout(applicationConfigRepository.getConfigValueByKey(keys[6]));
				Dashboard.clientConfigurationSettings.setYear(applicationConfigRepository.getConfigValueByKey(keys[7]));
				Dashboard.clientConfigurationSettings.setApplicationNameInheader(applicationConfigRepository.getConfigValueByKey(keys[8]));
				Dashboard.clientConfigurationSettings.setApplicationNameInLogin(applicationConfigRepository.getConfigValueByKey(keys[9]));
				Dashboard.clientConfigurationSettings.setIsApplicationLogoAvailable(Short.parseShort(applicationConfigRepository.getConfigValueByKey(keys[10])));
				Dashboard.clientConfigurationSettings.setApplicationTitleName(applicationConfigRepository.getConfigValueByKey(keys[11]));
				CommonConstants.DATE_FORMAT = applicationConfigRepository.getConfigValueByKey(keys[12]);
				Dashboard.clientConfigurationSettings.setDateFormat(CommonConstants.DATE_FORMAT);
				Dashboard.clientConfigurationSettings.setAutoSaveTimeout(Integer.parseInt(applicationConfigRepository.getConfigValueByKey(keys[13])));
				CommonConstants.IS_TIME_REQUIRED = applicationConfigRepository.getConfigValueByKey(keys[14]);
				Dashboard.clientConfigurationSettings.setOnlyWords(Integer.parseInt(applicationConfigRepository.getConfigValueByKey(keys[15])));
				Dashboard.clientConfigurationSettings.setIsTimeRequired(CommonConstants.IS_TIME_REQUIRED);
				Dashboard.clientConfigurationSettings.setIsTLSOn(applicationConfigRepository.getConfigValueByKey(keys[16]));
				Dashboard.clientConfigurationSettings.setIsSendEmailOn(Integer.parseInt(applicationConfigRepository.getConfigValueByKey(keys[17])));
				return ok("Client configuration Updated Successfully");
			}
		}
		catch (Exception exception)
		{
			CMSLogger.error(logger, "error loading the configuration", exception);
		}
		CMSLogger.info(logger, "Loading configuration from conf file");
		Dashboard.clientConfigurationSettings.setClient(Play.application().configuration().getString(keys[0]));
		Dashboard.clientConfigurationSettings.setVersion(Play.application().configuration().getString(keys[1]));
		Dashboard.clientConfigurationSettings.setRestBaseUrl(Play.application().configuration().getString(keys[2]));
		RestUrlAttribute.REST_BASE_URL = Play.application().configuration().getString(keys[2]);
		Dashboard.clientConfigurationSettings.setWebsocketUrl(Play.application().configuration().getString(keys[3]));
		Dashboard.clientConfigurationSettings.setRte(Play.application().configuration().getString(keys[4]));
		Dashboard.clientConfigurationSettings.setIsWebsocketEnabled(Play.application().configuration().getString(keys[5]));
		Dashboard.clientConfigurationSettings.setSessionTimeout(Play.application().configuration().getString(keys[6]));
		Dashboard.clientConfigurationSettings.setYear(Play.application().configuration().getString(keys[7]));
		Dashboard.clientConfigurationSettings.setApplicationNameInheader(Play.application().configuration().getString(keys[8]));
		Dashboard.clientConfigurationSettings.setApplicationNameInLogin(Play.application().configuration().getString(keys[9]));
		Dashboard.clientConfigurationSettings.setIsApplicationLogoAvailable(Short.parseShort(Play.application().configuration().getString(keys[10])));
		Dashboard.clientConfigurationSettings.setApplicationTitleName(Play.application().configuration().getString(keys[11]));
		CommonConstants.DATE_FORMAT = applicationConfigRepository.getConfigValueByKey(keys[12]);
		Dashboard.clientConfigurationSettings.setDateFormat(CommonConstants.DATE_FORMAT);
		Dashboard.clientConfigurationSettings.setAutoSaveTimeout(Integer.parseInt(Play.application().configuration().getString(keys[13])));
		CommonConstants.IS_TIME_REQUIRED = applicationConfigRepository.getConfigValueByKey(keys[14]);
		Dashboard.clientConfigurationSettings.setOnlyWords(Integer.parseInt(applicationConfigRepository.getConfigValueByKey(keys[15])));
		Dashboard.clientConfigurationSettings.setIsTimeRequired(CommonConstants.IS_TIME_REQUIRED);
		Dashboard.clientConfigurationSettings.setIsTLSOn(applicationConfigRepository.getConfigValueByKey(keys[16]));
		Dashboard.clientConfigurationSettings.setIsSendEmailOn(Integer.parseInt(applicationConfigRepository.getConfigValueByKey(keys[17])));
		return ok("Client configuration Updated Successfully");
	}
}