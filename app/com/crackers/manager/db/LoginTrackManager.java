package com.crackers.manager.db;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CrackersLogger;
import com.crackers.model.LoginTrack;
import com.crackers.model.User;
import com.crackers.repositories.UserRepository;

@Component
public class LoginTrackManager
{

	private Logger					logger	= Logger.getLogger(LoginTrackManager.class);
	@Resource
	private UserRepository			userRepository;

	public void loginTrack(LoginTrack loginTrack)
	{
		User user = userRepository.checkValidUser(loginTrack.getIdUser());
		if(user == null)
			return;
		user.setLoginTrack(loginTrack);
		userRepository.save(user);
		CrackersLogger.info(logger, "Login got Tracked");
	}
}