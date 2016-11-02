package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.dto.LoginTrackDto;
import com.crackers.model.LoginTrack;

@Component
public class LoginTrackTranslator
{

	private static Logger	logger	= Logger.getLogger(LoginTrackTranslator.class);

	public LoginTrack translateToLoginTrack(LoginTrackDto loginTrackDto)
	{
		LoginTrack loginTrack = new LoginTrack();
		try
		{
			BeanUtil.copyBeanProperties(loginTrackDto, loginTrack, new ArrayList<String>());
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			CMSLogger.error(logger, "Error while copying bean util in Login Track", e);
		}
		return loginTrack;
	}
}