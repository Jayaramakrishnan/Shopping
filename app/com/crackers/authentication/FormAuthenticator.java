package com.crackers.authentication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.exceptions.AccessDeninedException;
import com.crackers.model.User;
import com.crackers.model.UserCredential;
import com.crackers.model.UserSource;
import com.crackers.repositories.CredentialRepository;
import com.crackers.repositories.UserSourceRepository;
import com.crackers.util.PassEncryptUtil;

@Component
public class FormAuthenticator implements Authenticator
{

    @Resource
    private CredentialRepository credentialRepository;
    @Resource
    private UserSourceRepository userSourceRepository;
    @Resource
    private PassEncryptUtil      passEncryptUtil;
    private static Logger        logger = Logger.getLogger(FormAuthenticator.class);

    @Override
    public boolean authenticate(User user, String password) throws InvalidKeyException, NoSuchAlgorithmException
    {
        boolean valid = false;
        if (user.getIdSource() == null || user.getIdSource().intValue() != com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM).intValue())
        {
            CMSLogger.error(logger, "Confoguration mismatch in FORM", new AccessDeninedException());
            return valid;
        }
        UserCredential userCredential = credentialRepository.getCredentialByUserName(user.getUserName());
        if (userCredential != null)
        {
            CMSLogger.info(logger, "User Have Credentials");
            CMSLogger.info(logger, "Get the credential informations");
            String hashedKey = passEncryptUtil.encryptPassword(password, userCredential.getSaltKey(), CommonConstants.ENCRYPTION_ALGORITHM);
            if (hashedKey.equals(userCredential.getHashedKey()))
            {
                CMSLogger.info(logger, "Forms Authentication Successfully Compiled");
                CMSLogger.info(logger, userCredential.getHashedKey() + "#@@@@@@@@@@@@@" + hashedKey);
                valid = true;
            }
            else
            {
                CMSLogger.error(logger, "Exception at Forms type Authentictaion.", new AccessDeninedException());
                return valid;
            }
        }
        else
        {
            CMSLogger.error(logger, "Missing user credentials in FORMS.", new AccessDeninedException());
            return valid;
        }
        return valid;
    }

    @Override
    public List<UserSource> getUserSource()
    {
        CMSLogger.info(logger, "Form User sources:");
        List<UserSource> userSources = new ArrayList<>();
        userSources.add(userSourceRepository.findOne(com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM).longValue()));
        CMSLogger.info(logger, "User sources:" + userSources);
        return userSources;
    }
}