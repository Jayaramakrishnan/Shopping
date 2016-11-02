package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.services.UserService;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserHandler extends Handler
{

	private static Logger	logger	= Logger.getLogger(UserHandler.class);
	protected final String	USER	= "user";
	@Resource
	private UserService		userService;

	@Override
	public UserDto handleRequest(Integer idUser, UserDto userDto, Integer idCurrentUser, String changedList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, UnparseableDateTimeStringException, IOException
	{
		CMSLogger.info(logger, "Inside UserHandler");
		if (changedList.equalsIgnoreCase(USER))
		{
			return userService.updateUserDetails(CryptoBinderUtil.getDecryptId(userDto.getIdUser()), idCurrentUser, userDto);
		}
		return super.handleRequest(idUser, userDto, idCurrentUser, changedList);
	}
}
