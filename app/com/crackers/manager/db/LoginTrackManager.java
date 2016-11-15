package com.crackers.manager.db;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.model.LoginTrack;
import com.crackers.repositories.LoginTrackRepository;

@Component
public class LoginTrackManager
{

    private Logger               logger = Logger.getLogger(LoginTrackManager.class);
    @Resource
    private LoginTrackRepository loginTrackRepository;

    public void loginTrack(LoginTrack loginTrack)
    {
        loginTrackRepository.save(loginTrack);
        CMSLogger.info(logger, "Login got Tracked");
    }
}