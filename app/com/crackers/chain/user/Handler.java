package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;

@Component
public abstract class Handler
{

	private static Logger	logger	= Logger.getLogger(Handler.class);
	protected Handler		sucessor;

	public void setSucessor(Handler sucessor)
	{
		this.sucessor = sucessor;
	}

	public UserDto handleRequest(Integer idUser, UserDto userDto, Integer idCurrentUser, String changedList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, UnparseableDateTimeStringException, IOException
	{
		CMSLogger.info(logger, "Inside Handler");
		if (sucessor != null)
		{
			CMSLogger.info(logger, "Going to call sucessor");
			sucessor.handleRequest(idUser, userDto, idCurrentUser, changedList);
		}
		return userDto;
	}
}
