package com.crackers.authentication;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.common.RestUrlAttribute;
import com.crackers.exceptions.UnSupportedAuthenticationException;
import com.crackers.repositories.ApplicationConfigRepository;

@Component
public class AuthenticationFactory
{

    private static Logger               logger = Logger.getLogger(AuthenticationFactory.class);
    @Resource
    private FormAuthenticator           formAuthenticator;
    @Resource
    private ApplicationConfigRepository applicationConfigRepository;

    public Authenticator getAuthenticatorFactory() throws UnSupportedAuthenticationException
    {
        String auth = applicationConfigRepository.getConfigValueByKey("Auth setting");
        CMSLogger.info(logger, "Auth type:" + auth);
        if (auth.equals(RestUrlAttribute.FORMS_AUTHENTICATION))
        {
            return formAuthenticator;
        }
        else
        {
            throw new UnSupportedAuthenticationException();
        }
    }
}