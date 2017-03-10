package com.crackers.controllers.login;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CommonConstants;
import com.crackers.common.CrackersLogger;
import com.crackers.common.RestUrlAttribute;
import com.crackers.common.SendEMail;
import com.crackers.controllers.BaseController;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.RegistrationException;
import com.crackers.model.User;
import com.crackers.model.UserCredential;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.repositories.EmailTemplateRepository;
import com.crackers.repositories.UserRepository;
import com.crackers.services.UserService;
import com.crackers.util.CacheManager;
import com.crackers.util.CryptoBinderUtil;
import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

@Component
public class UserController extends BaseController {

	private static Logger				logger	= Logger.getLogger(UserController.class);
	@Resource
	private UserService					userService;
	@Resource
	private SendEMail					sendEMail;
	@Resource
	private ApplicationConfigRepository	applicationConfigRepository;
	@Resource
	private EmailTemplateRepository		emailTemplateRepository;
	@Resource
	private UserRepository				userRepository;
	@Resource
	private AuthenticationController	authenticationController;

	public Result getUserInfo(String uniqueId) throws Exception {
		CrackersLogger.info(logger, "Calling UserController getUserInfo method");
		/*
		 * Retrieving the user id from the cache if user id doesn't exist then the given token is invalid and its doesn't have access to this user.
		 */
		Long idUser = CacheManager.getIdUserFromCache(uniqueId);
		UserDto info = null;
		/*
		 * Verifying the idUser is not null or valid logged in user
		 */
		try {
			if (idUser != null) {
				info = userService.getUserList(idUser);
				if (info == null) {
					return noContent();
				}
			}
			else {
				return unauthorized("userid is empty / token not valid");
			}
		}
		catch (RegistrationException registrationException) {
			CrackersLogger.error(logger, "Error while getting User Info", registrationException);
			return unauthorized("User Object is null");
		}
		catch (Exception e) {
			CrackersLogger.error(logger, "Error while getting User Info", e);
			return internalServerError();
		}
		return ok(Json.toJson(info));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result createUser(String uniqueId) {
		JsonNode json = request().body().asJson();
		UserDto userDto = Json.fromJson(json, UserDto.class);
		if (uniqueId == null || userDto == null || userDto.getUserName() == null || userDto.getUserName().equals(RestUrlAttribute.EMPTY_QUOTES)) {
			return badRequest();
		}
		Long idUser = CacheManager.getIdUserFromCache(uniqueId);
		if (idUser == null) {
			CrackersLogger.error(logger, "Invalid UniqueId", null);
			return unauthorized();
		}
		if (userService.getUser(userDto.getUserName()) != 0) {
			return badRequest("User name all ready exist!!!");
		}
		UserDto userDtoCreated = new UserDto();
		List<String> changedList = new ArrayList<>();
		changedList.add("phoneNumber");
		changedList.add("Email");
		changedList.add("userCredential");
		userDto.setChangedList(changedList);
		CrackersLogger.info(logger, "The logged user is:" + idUser + "\n" + "UserTitle for new user is:" + userDto.getUserName());
		try {
			CrackersLogger.info(logger, "User Title is valid,Going to create new User.");
			if (userDto.getUserName() != RestUrlAttribute.EMPTY_QUOTES && userDto.getUserName() != null) {
				userDtoCreated = userService.createUser(idUser, userDto);
				if (userDtoCreated == null) {
					CrackersLogger.error(logger, "Problem while creating user", null);
					return noContent();
				}
				BeanUtil.copyBeanProperties(userDtoCreated, userDto, new ArrayList<>());
				BeanUtil.copyBeanProperties(userService.updateUser(idUser, userDto), userDtoCreated, new ArrayList<>());
				CrackersLogger.info(logger, "User is Created Successfully");
			}
			return ok(Json.toJson(userDtoCreated));
		}
		catch (Exception e) {
			CrackersLogger.error(logger, "Exception while user Creation", e);
			return internalServerError();
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result updateUser(String uniqueId) throws IllegalAccessException, IllegalArgumentException, RegistrationException, InvocationTargetException {
		CrackersLogger.info(logger, "User Controller Method Call:" + "createUser( )");
		JsonNode json = request().body().asJson();
		UserDto userDto = Json.fromJson(json, UserDto.class);
		if (uniqueId == null || userDto == null || userDto.getId() == null) {
			CrackersLogger.error(logger, "Invalid inputs while update", null);
			return badRequest();
		}
		Long idUser = CryptoBinderUtil.getDecryptId(userDto.getId());
		UserDto user = userService.getUserList(idUser);
		if (user != null && userDto.getUserName() != null && !(user.getUserName().equals(userDto.getUserName())) && userService.getUser(userDto.getUserName()) != 0) {
			CrackersLogger.error(logger, "User name allready exist.", null);
			return badRequest("User name all ready exist!!!");
		}
		List<String> changedList = new ArrayList<>();
		changedList.add("user");
		changedList.add("Email");
		changedList.add("address");
		changedList.add("Image");
		changedList.add("phoneNumber");
		changedList.add("userCredential");
		userDto.setChangedList(changedList);
		CrackersLogger.info(logger, "The logged user is:" + idUser + "\n" + "UserTitle for new user is:" + userDto.getUserName());
		try {
			CrackersLogger.info(logger, "User Title is valid,Going to create new User.");
			UserDto userDtoCreated = userService.getUserList(CryptoBinderUtil.getDecryptId(userDto.getId()));
			if (userDtoCreated == null) {
				CrackersLogger.error(logger, "Given user id is invalid", null);
				return badRequest();
			}
			BeanUtil.copyBeanProperties(userService.updateUser(idUser, userDto), userDtoCreated, new ArrayList<>());
			CrackersLogger.info(logger, "User is updated Successfully");
			Long changedUserId = CryptoBinderUtil.getDecryptId(userDto.getId());
			List<String> changedUniqueId = CacheManager.getUserTokenFromCache(changedUserId);
			User userInfo = userRepository.validateById(changedUserId);
			if (changedUniqueId == null || changedUniqueId.isEmpty()) {
				changedUniqueId = new ArrayList<>();
				changedUniqueId.add(UUID.randomUUID().toString());
			}
			CacheManager.setUserInfoToCache(changedUniqueId.get(0), userInfo);
			authenticationController.setUserRoleFunctionAccess(changedUniqueId.get(0));
			return ok(Json.toJson(userDtoCreated));
		}
		catch (Exception e) {
			CrackersLogger.error(logger, "Exception while user Updation", e);
			return internalServerError();
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result createForgetPassword() {
		JsonNode json = request().body().asJson();
		UserDto userDto = Json.fromJson(json, UserDto.class);
		if (userDto == null || userDto.getUserName() == null || userDto.getNewPassword() == null || userDto.getNewPassword() == RestUrlAttribute.EMPTY_QUOTES || userDto.getUserName() == RestUrlAttribute.EMPTY_QUOTES) {
			CrackersLogger.error(logger, "Invalid inputs", null);
			return badRequest();
		}
		try {
			Long idUser = userService.validateNewUserMailId(userDto.getUserName());
			if (idUser == null) {
				CrackersLogger.error(logger, "Mail id is invalid", null);
				return unauthorized();
			}
			String hashedKey = userService.getUserCredential(idUser);
			if (hashedKey != null && !hashedKey.equals(RestUrlAttribute.EMPTY_QUOTES)) {
				boolean passwordsList = userService.verifyPreviousPasswords(idUser, userDto.getNewPassword());
				CrackersLogger.info(logger, "passwordsList" + passwordsList);
				if (passwordsList) {
					CrackersLogger.info(logger, "IdUser is Valid " + idUser);
					UserCredential userCredential = userService.getUserCredentialObject(idUser);
					String newPassword = encryptPassword(userDto.getNewPassword(), CommonConstants.SALT_KEY, CommonConstants.ENCRYPTION_ALGORITHM);
					if (userCredential != null && !(hashedKey.equals(newPassword))) {
						Long credential = userService.createForgetPassword(idUser, userDto, userCredential.getSaltKey());
						userDto.setId(CryptoBinderUtil.getEncryptId(credential.toString()));
						CrackersLogger.info(logger, "Credentials saved");
					}
				}
				else {
					CrackersLogger.info(logger, "previous passwords are equal");
					return badRequest("Previous passwords are equal");
				}
			}
		}
		catch (Exception e) {
			CrackersLogger.error(logger, "Error while changing forget password for user", e);
			return internalServerError();
		}
		return ok(Json.toJson(userDto));
	}

	private String encryptPassword(String data, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
		CrackersLogger.info(logger, "Validation By  " + algorithm);
		SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(signingKey);
		byte[] rawSig = mac.doFinal(data.getBytes());
		return new String(Hex.encodeHex(rawSig));
	}
}