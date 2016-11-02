package com.crackers.authentication;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.exceptions.UnSupportedAuthenticationException;
import com.crackers.repositories.ApplicationConfigRepository;

@Component
public class AuthenticationFactory
{

	private static Logger				logger	= Logger.getLogger(AuthenticationFactory.class);
	private Authenticator				authenticator;
	@Resource
	private FormAuthenticator			formAuthenticator;
	@Resource
	private ApplicationConfigRepository	applicationConfigRepository;

	public Authenticator getAuthenticatorFactory() throws UnSupportedAuthenticationException
	{
		if (authenticator != null)
		{
			return authenticator;
		}
		String auth = applicationConfigRepository.getConfigValueByKey("Auth setting");
		CMSLogger.info(logger, "Auth type:" + auth);
		switch (auth)
		{
			case CommonConstants.FORMS_AUTHENTICATION:
				authenticator = formAuthenticator;
				break;
			default:
				throw new UnSupportedAuthenticationException();
		}
		return authenticator;
	}
}