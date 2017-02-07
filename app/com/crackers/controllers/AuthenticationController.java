package com.crackers.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import play.libs.Json;
import play.mvc.Result;

import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.dto.LoginTrackDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.AccessDeninedException;
import com.crackers.exceptions.RegistrationException;
import com.crackers.model.Role;
import com.crackers.model.User;
import com.crackers.repositories.UserRoleRepository;
import com.crackers.services.AutheticationService;
import com.crackers.services.LoginTrackService;
import com.crackers.util.CacheManager;

@Component
public class AuthenticationController extends BaseController
{

    private static Logger        logger = Logger.getLogger(AuthenticationController.class);
    @Resource
    private AutheticationService authenticationService;
    @Resource
    private UserRoleRepository   userRoleRepository;
    @Resource
    private LoginTrackService    loginTrackService;

    public Result authenticate()
    {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String userName = request().getHeader("username");
        String password = request().getHeader("password");
        try
        {
            final UserDto userDto = new UserDto();
            CMSLogger.info(logger, "Inside the Login controller checking the user name and password for empty");
            if (userName == null || password == null)
            {
                CMSLogger.info(logger, "user name or password is empty");
                return unauthorized("Invalid username or password");
            }
            CMSLogger.info(logger, "user name and password is not empty");
            userDto.setUserName(userName);
            CMSLogger.info(logger, "Validating the user using the authenticate service");
            Object validUser = authenticationService.validate(userDto, password);
            if (validUser == null)
            {
                CMSLogger.info(logger, "User is invalid");
                try
                {
                    LoginTrackDto loginTrackDto = new LoginTrackDto();
                    loginTrackDto.setUserDevice(request().getHeader(CommonConstants.USER_DEVICE_STRING));
                    loginTrackDto.setUserAgent(request().getHeader(CommonConstants.USER_AGENT_STRING));
                    loginTrackDto.setClientIp(request().getHeader(CommonConstants.CLIENT_IP_STRING));
                    loginTrackDto.setLoggedOnTime(ts);
                    loginTrackDto.setIsSuccess((short) 0);
                    loginTrackDto.setEmail(userName);
                    loginTrackDto.setCreatedOn(ts);
                    loginTrackDto.setIsSessionExpired((short) 0);
                    loginTrackService.loginTrackFailed(loginTrackDto);
                }
                catch (Exception e)
                {
                    return unauthorized("Invalid username or password" + e);
                }
                return unauthorized("Invalid username or password");
            }
            else
            {
                String uniqueID = null;
                if (validUser instanceof User)
                {
                    User userInfo = (User) validUser;
                    logger.info("User is valid, session has been created");
                    Integer idUser = userInfo.getIdUser();
                    uniqueID = UUID.randomUUID().toString();
                    CMSLogger.info(logger, "token from authenticationController" + uniqueID);
                    CacheManager.setIdUserToCache(uniqueID, idUser);
                    CacheManager.setUserInfoToCache(uniqueID, userInfo);
                    CacheManager.addUserTokenToCache(idUser, uniqueID);
                    CMSLogger.info(logger, "id" + CacheManager.getIdUserFromCache(uniqueID));
                    if (!setUserRoleFunctionAccess(uniqueID))
                    {
                        return unauthorized("Unauthorized");
                    }
                    try
                    {
                        LoginTrackDto loginTrackDto = new LoginTrackDto();
//                        loginTrackDto.setUser(userInfo);
                        loginTrackDto.setUserDevice(request().getHeader(CommonConstants.USER_DEVICE_STRING));
                        loginTrackDto.setUserAgent(request().getHeader(CommonConstants.USER_AGENT_STRING));
                        loginTrackDto.setClientIp(request().getHeader(CommonConstants.CLIENT_IP_STRING));
                        loginTrackDto.setLoggedOnTime(ts);
                        loginTrackDto.setSessionToken(uniqueID);
                        loginTrackDto.setIsSuccess((short) 1);
                        loginTrackDto.setCreatedBy(idUser);
                        loginTrackDto.setCreatedOn(ts);
                        loginTrackDto.setIsSessionExpired((short) 0);
                        loginTrackDto.setEmail(userName);
                        loginTrackService.loginTrack(uniqueID, loginTrackDto);
                    }
                    catch (Exception e)
                    {
                        CMSLogger.error(logger, "Error while updating login track", e);
                        return ok(Json.toJson(uniqueID));
                    }
                }
                return ok(Json.toJson(uniqueID));
            }
        }
        catch (RegistrationException registrationException)
        {
            CMSLogger.error(logger, "Registration Exception while authenticating ", registrationException);
            return noContent();
        }
        catch (AccessDeninedException AccessDeninedException)
        {
            CMSLogger.info(logger, "Not Authorized User");
            try
            {
                CMSLogger.error(logger, "Exception", AccessDeninedException);
                LoginTrackDto loginTrackDto = new LoginTrackDto();
                loginTrackDto.setUserDevice(request().getHeader(CommonConstants.USER_DEVICE_STRING));
                loginTrackDto.setUserAgent(request().getHeader(CommonConstants.USER_AGENT_STRING));
                loginTrackDto.setClientIp(request().getHeader(CommonConstants.CLIENT_IP_STRING));
                loginTrackDto.setLoggedOnTime(ts);
                loginTrackDto.setIsSuccess((short) 0);
                loginTrackDto.setEmail(userName);
                loginTrackDto.setCreatedOn(ts);
                loginTrackDto.setIsSessionExpired((short) 0);
                loginTrackService.loginTrackFailed(loginTrackDto);
            }
            catch (Exception e)
            {
                CMSLogger.error(logger, "Not Authorized User", e);
                return unauthorized("Not Authorized User" + e);
            }
            return unauthorized("Not Authorized User");
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Error while validting the user", exception);
            return unauthorized("Invalid username or password");
        }
    }

    public boolean setUserRoleFunctionAccess(String uniqueId)
    {
        try
        {
            Integer idUser = CacheManager.getIdUserFromCache(uniqueId);
            if (idUser == null)
            {
                return false;
            }
            List<Object[]> resourceObj = userRoleRepository.getUserResource(idUser);
            Role roles = userRoleRepository.getUserRole(idUser);
            Map<Integer, List<Integer>> resourceMap = new HashMap<>();
            for (Object[] obj : resourceObj)
            {
                if (resourceMap.containsKey((int) obj[2]))
                {
                    List<Integer> access = resourceMap.get((int) obj[2]);
                    access.add((int) obj[3]);
                    resourceMap.put((int) obj[2], access);
                }
                else
                {
                    List<Integer> access = new ArrayList<>();
                    access.add((int) obj[3]);
                    resourceMap.put((int) obj[2], access);
                }
            }
            CMSLogger.debug(logger, "UniqueId:" + uniqueId);
            CacheManager.setUserRoleToCache(uniqueId, roles);
            if (CacheManager.getFunctionAccessFromCache(uniqueId) != null)
            {
                CacheManager.removeFunctionAccessFromCache(uniqueId);
                CacheManager.setFunctionAccessToCache(uniqueId, resourceMap);
            }
            else
            {
                CacheManager.setFunctionAccessToCache(uniqueId, resourceMap);
            }
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Error in Setting Functional Access to Cache ", e);
        }
        return true;
    }
}
