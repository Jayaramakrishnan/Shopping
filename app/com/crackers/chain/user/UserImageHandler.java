package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserImageHandler extends Handler
{

	private static Logger	logger	= Logger.getLogger(UserImageHandler.class);
	protected final String	IMAGE	= "Image";
	@Resource
	private UserManager		userManager;

	@Override
	public UserDto handleRequest(Integer idUser, UserDto userDto, Integer idCurrentUser, String changedList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, UnparseableDateTimeStringException, IOException
	{
		if (changedList.equalsIgnoreCase(IMAGE) && userDto.getImageDto() != null)
		{
			CMSLogger.info(logger, "Inside IMAGE");
			return userManager.updateUserImageDetails(CryptoBinderUtil.getDecryptId(userDto.getIdUser()), userDto.getImageDto(), idCurrentUser);
		}
		return super.handleRequest(idUser, userDto, idCurrentUser, changedList);
	}
}