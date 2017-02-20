package com.crackers.controllers;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CommonConstants;
import com.crackers.common.CrackersLogger;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.UserDto;
import com.crackers.model.Role;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.util.CacheManager;
import com.crackers.vo.ClientConfigurationVO;
import com.crackers.vo.UserSessionSettings;
import com.crackers.vo.UserVO;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;

@Component
public class Dashboard extends BaseController
{

    private static Logger               logger              = Logger.getLogger(Dashboard.class);
    public String                       idResource          = RestUrlAttribute.EMPTY_QUOTES;
    public String                       idGeneric           = RestUrlAttribute.EMPTY_QUOTES;
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
                CrackersLogger.info(logger, "In Dashboard idGeneric: " + idGeneric + " idResource: " + idResource);
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
                    userInfoVO.setIdRole(userRole.getId());
                    userInfoVO.setRole(userRole.getRole());
                    userInfoVO.setImageColorCode(userInfo.getImageColorCode());
                }
                UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                ReadableUserAgent agent = parser.parse(request().getHeader(CommonConstants.USER_AGENT_STRING));
                String resourceId = idResource;
                String genericId = idGeneric;
                idResource = RestUrlAttribute.EMPTY_QUOTES;
                idGeneric = RestUrlAttribute.EMPTY_QUOTES;
                if (request().getQueryString("url") != null && !request().getQueryString("url").equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES))
                {
                    CrackersLogger.info(logger, "Setting the redirect Url from Query string '");
                    session().put(CommonConstants.REDIRECT_URL, request().getQueryString("url"));
                }
                String redirectUrl = session().get(CommonConstants.REDIRECT_URL);
                session().put(CommonConstants.REDIRECT_URL, "");
                userSessionSettings = new UserSessionSettings();
                userSessionSettings.setResourceId(resourceId);
                userSessionSettings.setGenericId(genericId);
                userSessionSettings.setRedirectUrl(redirectUrl);
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
            CrackersLogger.error(logger, "Error while loading the dashboard ", exception);
            session().clear();
        }
        return redirect("/");
    }

    public Result redirectResource()
    {
        DynamicForm requestData = Form.form().bindFromRequest();
        String selectedResourceId = requestData.get("idResource");
        String selectedGenericId = requestData.get("idGeneric");
        String redirectionCompleteURL = requestData.get("redirectURL");
        CrackersLogger.info(logger, "selectedResourceId: " + selectedResourceId + " selectedGenericId: " + selectedGenericId + "redirectionCompleteURL" + redirectionCompleteURL);
        if (selectedGenericId != null && !selectedGenericId.trim().equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES) && !selectedGenericId.trim().equalsIgnoreCase(zero))
        {
            idGeneric = selectedGenericId;
            if (selectedResourceId != null && !selectedResourceId.trim().equalsIgnoreCase(RestUrlAttribute.EMPTY_QUOTES) && !selectedResourceId.trim().equalsIgnoreCase(zero))
            {
                idResource = selectedResourceId;
            }
        }
        CrackersLogger.info(logger, "After checking redirection idGeneric: " + idGeneric + " idResource: " + idResource);
        return redirect("/DashBoard");
    }
}