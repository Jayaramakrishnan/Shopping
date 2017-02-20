package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CrackersLogger;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserImageHandler extends Handler
{

    private static Logger logger = Logger.getLogger(UserImageHandler.class);
    private String        image  = "Image";
    @Resource
    private UserManager   userManager;

    @Override
    public UserDto handleRequest(Long idUser, UserDto userDto, Long idCurrentUser, String changedList) throws InvocationTargetException, UnparseableDateTimeStringException, IOException
    {
        if (changedList.equalsIgnoreCase(image) && userDto.getImageDto() != null)
        {
            CrackersLogger.info(logger, "Inside IMAGE");
            return userManager.updateUserImageDetails(CryptoBinderUtil.getDecryptId(userDto.getIdUser()), userDto.getImageDto(), idCurrentUser);
        }
        return super.handleRequest(idUser, userDto, idCurrentUser, changedList);
    }
}