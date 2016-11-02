package com.crackers.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.chain.user.Handler;
import com.crackers.chain.user.UserAddressHandler;
import com.crackers.chain.user.UserEmailHandler;
import com.crackers.chain.user.UserHandler;
import com.crackers.chain.user.UserImageHandler;
import com.crackers.chain.user.UserPhoneHandler;
import com.crackers.common.CMSLogger;
import com.crackers.controllers.UserController;
import com.crackers.dto.ContactDetailsDto;
import com.crackers.dto.EmailDto;
import com.crackers.dto.ImageDto;
import com.crackers.dto.PhoneNumberDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.RegistrationException;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.model.ContactDetails;
import com.crackers.model.Email;
import com.crackers.model.Password;
import com.crackers.model.PhoneNumber;
import com.crackers.model.User;
import com.crackers.model.UserCredential;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.repositories.ImageColorCodeRepository;
import com.crackers.repositories.UserRoleRepository;
import com.crackers.translators.ContactDetailsTranslator;
import com.crackers.translators.EmailTranslator;
import com.crackers.translators.PersonTranslator;
import com.crackers.translators.PhoneNumberTranslator;
import com.crackers.translators.RoleTranslator;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserService
{

	private Logger						logger	= Logger.getLogger(UserService.class);
	@Resource
	private UserManager					userManager;
	@Resource
	private PersonTranslator			personTranslator;
	@Resource
	private EmailTranslator				emailTranslator;
	@Resource
	private UserHandler					userHandler;
	@Resource
	private UserImageHandler			userImageHandler;
	@Resource
	private UserAddressHandler			userAddressHandler;
	@Resource
	private UserPhoneHandler			userPhoneHandler;
	@Resource
	private UserEmailHandler			userEmailHandler;
	@Resource
	private UserRoleRepository			userRoleRepository;
	@Resource
	private RoleTranslator				roleTranslator;
	@Resource
	private UserController				userController;
	@Resource
	private ImageColorCodeRepository	imageColorCodeRepository;
	@Resource
	private ApplicationConfigRepository	applicationConfigRepository;
	@Resource
	private ImageService				imageService;
	@Resource
	private PhoneNumberTranslator		phoneNumberTranslator;
	@Resource
	private ContactDetailsTranslator	contactDetailsTranslator;

	public UserDto getUserList(Integer idUser) throws RegistrationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService getUserList method");
		CMSLogger.debug(logger, "Calling UserService:" + "getUserList()");
		com.crackers.model.User users = userManager.getCaseUserList(idUser);
		if (users == null)
		{
			return null;
		}
		UserDto userDtos = personTranslator.translateUserToDto(users);
		userDtos.setRoleDto(roleTranslator.translateToRoleDto(userRoleRepository.getRoleByIdUser(CryptoBinderUtil.getDecryptId(userDtos.getIdUser()))));
		if (userDtos.getIdImageColorCode() != null)
		{
			userDtos.setImageColorCode(imageColorCodeRepository.getImageColorCode(userDtos.getIdImageColorCode()));
		}
		userDtos = userManager.getUserMasterDto(userDtos);
		return userDtos;
	}

	public UserDto getImage(Integer idUser) throws RegistrationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService getImage method");
		CMSLogger.debug(logger, "Calling UserService:" + "getImage()" + idUser);
		ImageDto imageDto = imageService.getImageDto(idUser);
		UserDto userDto = new UserDto();
		userDto.setImageDto(imageDto);
		return userDto;
	}

	public UserDto createUser(int idUser, UserDto userDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		User user = personTranslator.translateToPerson(userDto);
		User createdUser = userManager.createUser(idUser, user);
		UserDto cDto = personTranslator.translateUserToDto(createdUser);
		UserDto dto = userManager.getUserMasterDto(cDto);
		dto.setImageColorCode(imageColorCodeRepository.getImageColorCode(dto.getIdImageColorCode()));
		CMSLogger.info(logger, "Translted UserDto is : " + dto.getUserName());
		return dto;
	}

	public Long getUser(String userName)
	{
		return userManager.getUser(userName);
	}

	public UserDto updateUserDetails(Integer idUser, int idCurrentUser, UserDto userDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		User user = personTranslator.translateToPerson(userDto);
		User createdUser = userManager.updateUser(idUser, idCurrentUser, user);
		UserDto cDto = personTranslator.translateUserToDto(createdUser);
		UserDto dto = userManager.getUserMasterDto(cDto);
		CMSLogger.info(logger, "Translted UserDto is : " + dto.getUserName());
		return dto;
	}

	public List<PhoneNumberDto> getPhoneNumberList(Integer idUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService getPhoneNumberList method");
		List<PhoneNumber> phoneNumber = userManager.getUserPhoneNumbers(idUser);
		List<PhoneNumberDto> phoneNumberDtos = phoneNumberTranslator.translateListToPhoneNumberDto(phoneNumber);
		List<PhoneNumberDto> dtos = new ArrayList<>();
		for (PhoneNumberDto phoneNumberUpdated : phoneNumberDtos)
		{
			if (phoneNumberUpdated.getPhoneTypeDto() != null)
			{
				phoneNumberUpdated.setPhoneTypeDto(userManager.getPhoneType(phoneNumberUpdated.getPhoneTypeDto().getIdPhoneType()));
			}
			dtos.add(phoneNumberUpdated);
		}
		return dtos;
	}

	public PhoneNumberDto createPhoneNumber(Integer idUser, int idCurrentUser, PhoneNumberDto phoneNumberDto) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService createPhoneNumberList method");
		PhoneNumber phoneNumbergiven = phoneNumberTranslator.translateDtoToPhoneNumber(phoneNumberDto);
		PhoneNumber updatedPhoneNumber = userManager.createPhoneNumber(idUser, phoneNumbergiven, idCurrentUser);
		return phoneNumberTranslator.translateToPhoneNumberDto(updatedPhoneNumber);
	}

	public PhoneNumberDto updatePhoneNumberList(Integer idUser, int idCurrentUser, PhoneNumberDto phoneNumberDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService updatePhoneNumberList method");
		PhoneNumber phoneNumbergiven = phoneNumberTranslator.translateDtoToPhoneNumber(phoneNumberDto);
		PhoneNumber updatedPhoneNumber = userManager.updatePhoneNumber(idUser, phoneNumbergiven, idCurrentUser);
		PhoneNumberDto phoneNumberUpdated = phoneNumberTranslator.translateToPhoneNumberDto(updatedPhoneNumber);
		if (phoneNumberUpdated.getPhoneTypeDto() != null)
		{
			phoneNumberUpdated.setPhoneTypeDto(userManager.getPhoneType(phoneNumberUpdated.getPhoneTypeDto().getIdPhoneType()));
		}
		return phoneNumberUpdated;
	}

	public List<EmailDto> getEmailList(Integer idUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService's:" + "getEmailList" + "(" + idUser + ")");
		List<Email> emails = userManager.getUserEmails(idUser);
		return emailTranslator.translateListToEmailDto(emails);
	}

	public EmailDto updateEmailList(Integer idUser, int idCurrentUser, EmailDto emailDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService updateEmailList method");
		Email email = emailTranslator.translateDtoToEmail(emailDto);
		Email updatedEmail = userManager.updateEmail(idUser, email, idCurrentUser);
		return emailTranslator.translateToEmailDto(updatedEmail);
	}

	public EmailDto createEmail(Integer idUser, int idCurrentUser, EmailDto emailDto) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService createEmail method");
		CMSLogger.debug(logger, "Calling UserService's:" + "createEmail" + "(" + idUser + "," + emailDto + "," + idCurrentUser + ")");
		Email email = emailTranslator.translateDtoToEmail(emailDto);
		Email updatedPhoneNumber = userManager.createEmail(idUser, email, idCurrentUser);
		return emailTranslator.translateToEmailDto(updatedPhoneNumber);
	}

	public UserDto updateUser(int idUser, UserDto userDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, UnparseableDateTimeStringException, IOException
	{
		CMSLogger.info(logger, "Calling UserService updateUser method");
		CMSLogger.debug(logger, "Calling UserService:" + "updateUser" + "(" + userDto + ")");
		CMSLogger.info(logger, "Am switch User value" + userDto.getChangedList());
		Handler h1 = userHandler;
		Handler h2 = userImageHandler;
		Handler h3 = userEmailHandler;
		Handler h4 = userPhoneHandler;
		Handler h5 = userAddressHandler;
		h1.setSucessor(h2);
		h2.setSucessor(h3);
		h3.setSucessor(h4);
		h4.setSucessor(h5);
		Iterator<String> changedIterator = userDto.getChangedList().iterator();
		UserDto dto = new UserDto();
		while (changedIterator.hasNext())
		{
			String changedList = changedIterator.next();
			CMSLogger.info(logger, h1 + "Going to call Handler");
			dto = h1.handleRequest(CryptoBinderUtil.getDecryptId(userDto.getIdUser()), userDto, idUser, changedList);
		}
		return dto;
	}

	public List<ContactDetailsDto> getContactDetailsList(Integer idUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService getEmailList method");
		CMSLogger.debug(logger, "Calling UserService's:" + "getEmailList" + "(" + idUser + ")");
		List<ContactDetails> emails = userManager.getUserContactDetails(idUser);
		return contactDetailsTranslator.translateToDto(emails);
	}

	public ContactDetailsDto updateContactDetailsList(Integer idUser, Integer idCurrentUser, ContactDetailsDto contactDetailsDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService updateContactDetailsList method");
		CMSLogger.debug(logger, "Calling UserService's:" + "updateContactDetailsList" + "(" + idUser + "," + contactDetailsDto + "," + idCurrentUser + ")");
		ContactDetails contactDetails = contactDetailsTranslator.translateToContactDetails(contactDetailsDto);
		ContactDetails updatedContactDetails = userManager.updateContactDetails(idUser, contactDetails, idCurrentUser);
		return contactDetailsTranslator.translateToContactDetailsDto(updatedContactDetails);
	}

	public ContactDetailsDto createContactDetails(Integer idUser, Integer idCurrentUser, ContactDetailsDto exteEntityDto) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService createContactDetails method");
		CMSLogger.debug(logger, "Calling UserService's:" + "createContactDetails" + "(" + idUser + "," + exteEntityDto + "," + idCurrentUser + ")");
		ContactDetails contactDetails = contactDetailsTranslator.translateToContactDetails(exteEntityDto);
		ContactDetails updatedContactDetails = userManager.createContactDetails(idUser, contactDetails, idCurrentUser);
		return contactDetailsTranslator.translateToContactDetailsDto(updatedContactDetails);
	}

	public ImageDto getUserImageById(Integer idUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Calling UserService getImage method");
		return imageService.getImageDto(idUser);
	}

	public Integer validateNewUserMailId(String emailId)
	{
		return userManager.validateNewUserMailId(emailId);
	}

	public String getUserCredential(Integer idUser)
	{
		return userManager.getUserCredential(idUser);
	}

	public UserCredential getUserCredentialObject(Integer idUser)
	{
		return userManager.getUserCredentialObject(idUser);
	}

	public Integer createForgetPassword(Integer idUser, UserDto userDto, String saltKey) throws InvalidKeyException, NoSuchAlgorithmException
	{
		return userManager.createForgetPassword(idUser, userDto, saltKey);
	}

	public UserCredential createUserCredentialObject(Integer idUser, String password) throws InvalidKeyException, NoSuchAlgorithmException
	{
		return userManager.createUserCredentialObject(idUser, password);
	}

	public Password createPasswordEntry(String encryptText, String email, Integer idUser, String saltKey)
	{
		return userManager.createPasswordEntry(encryptText, email, idUser, saltKey);
	}

	public Password getPasswordObject(Integer idPassword)
	{
		return userManager.getPasswordObject(idPassword);
	}

	public List<Password> expireLinks(Integer idUser)
	{
		return userManager.expireLinks(idUser);
	}

	public boolean verifyPreviousPasswords(Integer idUser, String newPassword) throws InvalidKeyException, NoSuchAlgorithmException
	{
		CMSLogger.info(logger, "Inside verifyPreviousPasswords" + idUser);
		return userManager.verifyPreviousPasswords(idUser, newPassword);
	}

	public User checkValidUser(Integer idUser)
	{
		CMSLogger.info(logger, "Inside checkValidUser" + idUser);
		return userManager.checkValidUser(idUser);
	}

	public Password getPasswordExpiredObject(Integer idPassword)
	{
		return userManager.getPasswordExpiredObject(idPassword);
	}
}