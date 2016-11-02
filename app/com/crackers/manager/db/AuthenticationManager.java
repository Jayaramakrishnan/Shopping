package com.crackers.manager.db;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.authentication.AuthenticationFactory;
import com.crackers.authentication.Authenticator;
import com.crackers.common.CMSLogger;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.AccessDeninedException;
import com.crackers.exceptions.RegistrationException;
import com.crackers.model.User;
import com.crackers.repositories.CredentialRepository;
import com.crackers.repositories.UserRepository;

@Component
public class AuthenticationManager
{

	private static Logger			logger	= Logger.getLogger(AuthenticationManager.class);
	@Resource
	private UserRepository			userRepository;
	@Resource
	private CredentialRepository	credentialRepository;
	@Resource
	private AuthenticationFactory	authenticationFactory;

	public User validate(UserDto userDto, String secret) throws Exception
	{
		CMSLogger.info(logger, "Authentication manager starts verifying");
		/*
		 * Verify the given inputs are null or not.if null it will return BadRequestException else it will process the input.
		 */
		if (userDto != null && secret != null)
		{
			/*
			 * Used to hold response status from authenticators.
			 */
			boolean valid = false;
			/*
			 * Check the Logged user is registered user or not. it will take user name as a input return the user object.
			 */
			User validUser = userRepository.validateByUserName(userDto.getUserName());
			/*
			 * Check the user object id is null or not.If it is not null process the inputs.
			 */
			if (validUser == null)
			{
				CMSLogger.error(logger, "Not Registrated user", new RegistrationException());
				/*
				 * If user object is null, it return the RegistrationException.
				 */
				throw new RegistrationException();
			}
			User IsActiveUser = userRepository.checkValidUser(validUser.getIdUser());
			if (IsActiveUser == null)
			{
				CMSLogger.error(logger, "In active user, Unauthorized to access", new RegistrationException());
				/*
				 * If user object is null, it return the RegistrationException.
				 */
				throw new AccessDeninedException();
			}
			/*
			 * Call AuthenticationFactory for getting Authentication type.It may return the UnsupportedAuthenticationException if Configuration variable is null.
			 */
			Authenticator login = authenticationFactory.getAuthenticatorFactory();
			/*
			 * Since the Given user name is registered user, We have to verify the secret key.
			 */
			valid = login.authenticate(validUser, secret);
			/*
			 * It returns the user object, if he/she is authenticated user.
			 */
			if (valid)
			{
				return validUser;
			}
			/*
			 * It returns the AccessDeninedException, if he/she is UnAuthorized user.
			 */
			else
			{
				CMSLogger.error(logger, "AccessDenined user", new AccessDeninedException());
				throw new AccessDeninedException();
			}
		}
		return null;
	}
}