package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CrackersLogger;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.services.UserService;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserHandler extends Handler
{

    private static Logger logger = Logger.getLogger(UserHandler.class);
    private String        user   = "user";
    @Resource
    private UserService   userService;

    @Override
    public UserDto handleRequest(Long idUser, UserDto userDto, Long idCurrentUser, String changedList) throws InvocationTargetException, UnparseableDateTimeStringException, IOException
    {
        if (changedList.equalsIgnoreCase(user))
        {
        	CrackersLogger.info(logger, "Inside UserHandler");
            return userService.updateUserDetails(CryptoBinderUtil.getDecryptId(userDto.getIdUser()), idCurrentUser, userDto);
        }
        return super.handleRequest(idUser, userDto, idCurrentUser, changedList);
    }
}
