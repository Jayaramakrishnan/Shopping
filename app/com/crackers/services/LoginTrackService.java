package com.crackers.services;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CrackersLogger;
import com.crackers.common.DateStringUtil;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.LoginTrackDto;
import com.crackers.manager.db.LoginTrackManager;
import com.crackers.model.LoginTrack;
import com.crackers.repositories.LoginTrackRepository;
import com.crackers.translators.LoginTrackTranslator;

@Component
public class LoginTrackService {

	private Logger					logger	= Logger.getLogger(LoginTrackService.class);
	@Resource
	private LoginTrackManager		loginTrackManager;
	@Resource
	private LoginTrackTranslator	loginTrackTranslator;
	@Resource
	private LoginTrackRepository	loginTrackRepository;

	public void loginTrack(String uniqueId, LoginTrackDto loginTrackDto) {
		CrackersLogger.info(logger, "Inside LoginTrackService: loginTrack");
		LoginTrack loginTrack = loginTrackTranslator.translateToLoginTrack(loginTrackDto);
		loginTrackManager.loginTrack(loginTrack);
	}

	public void loginTrackFailed(LoginTrackDto loginTrackDto) {
		CrackersLogger.info(logger, "Inside LoginTrackService: loginTrackFailed");
		LoginTrack loginTrack = loginTrackTranslator.translateToLoginTrack(loginTrackDto);
		loginTrackManager.loginTrack(loginTrack);
	}

	public void loginTrackLogout(String uniqueId, boolean isForSession) {
		CrackersLogger.info(logger, "Inside LoginTrackService: loginTrackLogout " + uniqueId + isForSession);
		if (uniqueId == null || uniqueId.equals(RestUrlAttribute.EMPTY_QUOTES)) {
			CrackersLogger.info(logger, "UniqueId is Null and cannot Clear Session");
		}
		LoginTrack loginTrack = loginTrackRepository.getLoginTrack(uniqueId);
		if (loginTrack != null) {
			Long ts = DateStringUtil.getCurrentTimestamp();
			loginTrack.setLoggedOut(ts);
			if (isForSession) {
				loginTrack.setIsSessionExpired((short) 1);
			}
			loginTrackManager.loginTrack(loginTrack);
		}
	}
}