package com.crackers.services;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.LoginTrackDto;
import com.crackers.manager.db.LoginTrackManager;
import com.crackers.model.LoginTrack;
import com.crackers.repositories.LoginTrackRepository;
import com.crackers.translators.LoginTrackTranslator;

@Component
public class LoginTrackService
{

	private Logger					logger	= Logger.getLogger(LoginTrackService.class);
	@Resource
	private LoginTrackManager		loginTrackManager;
	@Resource
	private LoginTrackTranslator	loginTrackTranslator;
	@Resource
	private LoginTrackRepository	loginTrackRepository;

	public void loginTrack(String uniqueId, LoginTrackDto loginTrackDto)
	{
		CMSLogger.info(logger, "Inside LoginTrackService: loginTrack");
		LoginTrack loginTrack = loginTrackTranslator.translateToLoginTrack(loginTrackDto);
		loginTrackManager.loginTrack(loginTrack);
	}

	public void loginTrackFailed(LoginTrackDto loginTrackDto)
	{
		CMSLogger.info(logger, "Inside LoginTrackService: loginTrackFailed");
		LoginTrack loginTrack = loginTrackTranslator.translateToLoginTrack(loginTrackDto);
		loginTrackManager.loginTrack(loginTrack);
	}

	public void loginTrackLogout(String uniqueId, boolean isForSession)
	{
		CMSLogger.info(logger, "Inside LoginTrackService: loginTrackLogout " + uniqueId);
		if (uniqueId == null || uniqueId.equals(RestUrlAttribute.EMPTY_QUOTES))
		{
			CMSLogger.info(logger, "Am Null and not going to Clear Session when logging out ");
		}
		LoginTrack loginTrackNew = loginTrackRepository.getLoginTrack(uniqueId);
		CMSLogger.info(logger, "loginTrackNew" + loginTrackNew);
		if (loginTrackNew != null)
		{
			Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			loginTrackNew.setLoggedOut(ts);
			if (isForSession)
			{
				loginTrackNew.setIsSessionExpired((short) 1);
			}
			CMSLogger.info(logger, "going to track");
			loginTrackManager.loginTrack(loginTrackNew);
		}
	}
}