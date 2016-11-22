package com.crackers.controllers;

import javax.annotation.Resource;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;

import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.UserDto;
import com.crackers.model.Role;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.util.CacheManager;
import com.crackers.vo.ClientConfigurationVO;
import com.crackers.vo.UserSessionSettings;
import com.crackers.vo.UserVO;

import views.html.desktopDashboard;
import views.html.mobileDashboard;
import views.html.tabletDashboard;

@Component
public class Dashboard extends BaseController
{

    private static Logger               logger              = Logger.getLogger(Dashboard.class);
    public String                       idResource          = RestUrlAttribute.EMPTY_QUOTES;
    public String                       idGeneric           = RestUrlAttribute.EMPTY_QUOTES;
    public Integer                      idEntityType        = 0;
    public String                       redirectActivityUrl = RestUrlAttribute.EMPTY_QUOTES;
    public static String                idActivity          = RestUrlAttribute.EMPTY_QUOTES;
    String                              zero                = "0";
    public static ClientConfigurationVO clientConfigurationSettings;
    public UserSessionSettings          userSessionSettings;
    @Resource
    private ApplicationConfigRepository applicationConfigRepository;

    public Result dashBoard()
    {
        try
        {
            if (!session().isEmpty() || session(CommonConstants.UNIQUE_ID) != null)
            {
                UserDto userInfo = (UserDto) Cache.get(session().get(CommonConstants.UNIQUE_ID));
                CMSLogger.info(logger, "In Dashboard idGeneric: " + idGeneric + " idResource: " + idResource + " idEntityType: " + idEntityType);
                Role userRole = CacheManager.getUserRoleFromCache(session().get(CommonConstants.UNIQUE_ID));
                UserVO userInfoVO = new UserVO();
                if (userInfo != null)
                {
                    if (userInfo.getUserName() == null)
                    {
                        userInfoVO.setUserName(RestUrlAttribute.EMPTY_QUOTES);
                    }
                    else
                    {
                        userInfoVO.setUserName(userInfo.getUserName());
                    }
                    userInfoVO.setIdUser(userInfo.getIdUser());
                    userInfoVO.setIdRole(userRole.getIdRole());
                    userInfoVO.setRole(userRole.getRoleName());
                    userInfoVO.setImageColorCode(userInfo.getImageColorCode());
                }
                UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
                String resourceId = idResource;
                String genericId = idGeneric;
                Integer entityTypeId = idEntityType;
                idResource = RestUrlAttribute.EMPTY_QUOTES;
                idGeneric = RestUrlAttribute.EMPTY_QUOTES;
                idEntityType = 0;
                if (request().getQueryString("url") != null && !request().getQueryString("url").equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES))
                {
                    CMSLogger.info(logger, "Setting the redirect Url from Query string '");
                    session().put(CommonConstants.REDIRECT_URL, request().getQueryString("url"));
                }
                if (idActivity != null && !(idActivity.equals(RestUrlAttribute.EMPTY_QUOTES)))
                {
                    session().put(CommonConstants.REDIRECT_URL, redirectActivityUrl);
                }
                String redirectUrl = session().get(CommonConstants.REDIRECT_URL);
                session().put(CommonConstants.REDIRECT_URL, "");
                userSessionSettings = new UserSessionSettings();
                userSessionSettings.setResourceId(resourceId);
                userSessionSettings.setGenericId(genericId);
                userSessionSettings.setEntityTypeId(entityTypeId);
                userSessionSettings.setRedirectUrl(redirectUrl);
                userSessionSettings.setRedirectIdActivity(idActivity);
                idActivity = RestUrlAttribute.EMPTY_QUOTES;
                redirectActivityUrl = RestUrlAttribute.EMPTY_QUOTES;
                if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_SMART_PHONE))
                {
                    logger.info("Device:" + agent.getDeviceCategory().getName());
                    // return ok(mobileDashboard.render(userInfoVO, clientConfigurationSettings, userSessionSettings));
                    return ok();
                }
                else if (agent.getDeviceCategory().getName().equals(CommonConstants.USER_DEVICE_TABLET))
                {
                    logger.info("Device:" + agent.getDeviceCategory().getName());
                    // return ok(tabletDashboard.render(userInfoVO, clientConfigurationSettings, userSessionSettings));
                    return ok();
                }
                else
                {
                    // return ok(desktopDashboard.render(userInfoVO, clientConfigurationSettings, userSessionSettings));
                    return ok();
                }
            }
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Error while loading the dashboard ", exception);
            session().clear();
        }
        return redirect("/");
    }

    public Result redirectResource()
    {
        DynamicForm requestData = Form.form().bindFromRequest();
        String selectedResourceId = requestData.get("idResource");
        String selectedGenericId = requestData.get("idGeneric");
        String selectedEntityTypeId = requestData.get("idEntityType");
        String redirectionCompleteURL = requestData.get("redirectURL");
        String redirectIdActivity = requestData.get("idActivity");
        CMSLogger.info(logger, "selectedResourceId: " + selectedResourceId + " selectedGenericId: " + selectedGenericId + " selectedEntityTypeId: " + selectedEntityTypeId + "redirectionCompleteURL" + redirectionCompleteURL);
        if (redirectionCompleteURL != null && !redirectionCompleteURL.trim().equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES) && !redirectionCompleteURL.trim().equalsIgnoreCase(zero))
        {
            if (redirectIdActivity != null && !redirectIdActivity.trim().equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES) && !redirectIdActivity.trim().equalsIgnoreCase(zero))
            {
                idActivity = redirectIdActivity;
                redirectActivityUrl = redirectionCompleteURL;
            }
        }
        else if (selectedGenericId != null && !selectedGenericId.trim().equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES) && !selectedGenericId.trim().equalsIgnoreCase(zero))
        {
            idGeneric = selectedGenericId;
            if (selectedResourceId != null && !selectedResourceId.trim().equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES) && !selectedResourceId.trim().equalsIgnoreCase(zero))
            {
                idResource = selectedResourceId;
            }
            if (selectedEntityTypeId != null && !selectedEntityTypeId.trim().equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES) && !selectedEntityTypeId.trim().equalsIgnoreCase(zero))
            {
                idEntityType = Integer.parseInt(selectedEntityTypeId);
            }
        }
        CMSLogger.info(logger, "After checking redirection idGeneric: " + idGeneric + " idResource: " + idResource + " idEntityType: " + idEntityType);
        return redirect("/DashBoard");
    }
}