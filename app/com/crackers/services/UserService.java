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
import com.crackers.common.CrackersLogger;
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
import com.crackers.repositories.RoleRepository;
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
	private RoleRepository				roleRepository;
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

	public UserDto getUserList(Long idUser) throws RegistrationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService getUserList method");
		com.crackers.model.User user = userManager.getUser(idUser);
		if (user == null)
		{
			return null;
		}
		UserDto userDto = personTranslator.translateUserToDto(user);
		userDto.setRoleDto(roleTranslator.translateToRoleDto(roleRepository.getUserRole(CryptoBinderUtil.getDecryptId(userDto.getIdUser()))));
		if (userDto.getIdImageColorCode() != null)
		{
			userDto.setImageColorCode(imageColorCodeRepository.getImageColorCode(userDto.getIdImageColorCode()));
		}
		userDto = userManager.getUserMasterDto(userDto);
		return userDto;
	}

	public UserDto getImage(Long idUser) throws RegistrationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService getImage method");
		CrackersLogger.debug(logger, "Calling UserService:" + "getImage()" + idUser);
		ImageDto imageDto = imageService.getImageDto(idUser);
		UserDto userDto = new UserDto();
		userDto.setImageDto(imageDto);
		return userDto;
	}

	public UserDto createUser(Long idUser, UserDto userDto) throws InvocationTargetException
	{
		User user = personTranslator.translateToPerson(userDto);
		User createdUser = userManager.createUser(idUser, user);
		UserDto cDto = personTranslator.translateUserToDto(createdUser);
		UserDto dto = userManager.getUserMasterDto(cDto);
		dto.setImageColorCode(imageColorCodeRepository.getImageColorCode(dto.getIdImageColorCode()));
		return dto;
	}

	public Long getUser(String userName)
	{
		return userManager.getUser(userName);
	}

	public UserDto updateUserDetails(Long idUser, Long idCurrentUser, UserDto userDto) throws InvocationTargetException
	{
		User user = personTranslator.translateToPerson(userDto);
		User createdUser = userManager.updateUser(idUser, idCurrentUser, user);
		UserDto cDto = personTranslator.translateUserToDto(createdUser);
		UserDto dto = userManager.getUserMasterDto(cDto);
		CrackersLogger.info(logger, "Translted UserDto is : " + dto.getUserName());
		return dto;
	}

	public List<PhoneNumberDto> getPhoneNumberList(Long idUser) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService getPhoneNumberList method");
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

	public PhoneNumberDto createPhoneNumber(Long idUser, Long idCurrentUser, PhoneNumberDto phoneNumberDto) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService createPhoneNumberList method");
		PhoneNumber phoneNumbergiven = phoneNumberTranslator.translateDtoToPhoneNumber(phoneNumberDto);
		PhoneNumber updatedPhoneNumber = userManager.createPhoneNumber(idUser, phoneNumbergiven, idCurrentUser);
		return phoneNumberTranslator.translateToPhoneNumberDto(updatedPhoneNumber);
	}

	public PhoneNumberDto updatePhoneNumberList(Long idUser, Long idCurrentUser, PhoneNumberDto phoneNumberDto) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService updatePhoneNumberList method");
		PhoneNumber phoneNumbergiven = phoneNumberTranslator.translateDtoToPhoneNumber(phoneNumberDto);
		PhoneNumber updatedPhoneNumber = userManager.updatePhoneNumber(idUser, phoneNumbergiven, idCurrentUser);
		PhoneNumberDto phoneNumberUpdated = phoneNumberTranslator.translateToPhoneNumberDto(updatedPhoneNumber);
		if (phoneNumberUpdated.getPhoneTypeDto() != null)
		{
			phoneNumberUpdated.setPhoneTypeDto(userManager.getPhoneType(phoneNumberUpdated.getPhoneTypeDto().getIdPhoneType()));
		}
		return phoneNumberUpdated;
	}

	public List<EmailDto> getEmailList(Long idUser) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService's:" + "getEmailList" + "(" + idUser + ")");
		List<Email> emails = userManager.getUserEmails(idUser);
		return emailTranslator.translateListToEmailDto(emails);
	}

	public EmailDto updateEmailList(Long idUser, Long idCurrentUser, EmailDto emailDto) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService updateEmailList method");
		Email email = emailTranslator.translateDtoToEmail(emailDto);
		Email updatedEmail = userManager.updateEmail(idUser, email, idCurrentUser);
		return emailTranslator.translateToEmailDto(updatedEmail);
	}

	public EmailDto createEmail(Long idUser, Long idCurrentUser, EmailDto emailDto) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService createEmail method");
		CrackersLogger.debug(logger, "Calling UserService's:" + "createEmail" + "(" + idUser + "," + emailDto + "," + idCurrentUser + ")");
		Email email = emailTranslator.translateDtoToEmail(emailDto);
		Email updatedPhoneNumber = userManager.createEmail(idUser, email, idCurrentUser);
		return emailTranslator.translateToEmailDto(updatedPhoneNumber);
	}

	public UserDto updateUser(Long idUser, UserDto userDto) throws InvocationTargetException, UnparseableDateTimeStringException, IOException
	{
		CrackersLogger.info(logger, "Calling UserService updateUser method " + userDto.getChangedList());
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
			CrackersLogger.info(logger, h1 + "Going to call Handler");
			dto = h1.handleRequest(CryptoBinderUtil.getDecryptId(userDto.getIdUser()), userDto, idUser, changedList);
		}
		return dto;
	}

	public List<ContactDetailsDto> getContactDetailsList(Long idUser) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService getEmailList method");
		CrackersLogger.debug(logger, "Calling UserService's:" + "getEmailList" + "(" + idUser + ")");
		List<ContactDetails> emails = userManager.getUserContactDetails(idUser);
		return contactDetailsTranslator.translateToDto(emails);
	}

	public ContactDetailsDto updateContactDetailsList(Long idUser, Long idCurrentUser, ContactDetailsDto contactDetailsDto) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService updateContactDetailsList method");
		CrackersLogger.debug(logger, "Calling UserService's:" + "updateContactDetailsList" + "(" + idUser + "," + contactDetailsDto + "," + idCurrentUser + ")");
		ContactDetails contactDetails = contactDetailsTranslator.translateToContactDetails(contactDetailsDto);
		ContactDetails updatedContactDetails = userManager.updateContactDetails(idUser, contactDetails, idCurrentUser);
		return contactDetailsTranslator.translateToContactDetailsDto(updatedContactDetails);
	}

	public ContactDetailsDto createContactDetails(Long idUser, Long idCurrentUser, ContactDetailsDto exteEntityDto) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService createContactDetails method");
		CrackersLogger.debug(logger, "Calling UserService's:" + "createContactDetails" + "(" + idUser + "," + exteEntityDto + "," + idCurrentUser + ")");
		ContactDetails contactDetails = contactDetailsTranslator.translateToContactDetails(exteEntityDto);
		ContactDetails updatedContactDetails = userManager.createContactDetails(idUser, contactDetails, idCurrentUser);
		return contactDetailsTranslator.translateToContactDetailsDto(updatedContactDetails);
	}

	public ImageDto getUserImageById(Long idUser) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Calling UserService getImage method");
		return imageService.getImageDto(idUser);
	}

	public Long validateNewUserMailId(String emailId)
	{
		return userManager.validateNewUserMailId(emailId);
	}

	public String getUserCredential(Long idUser)
	{
		return userManager.getUserCredential(idUser);
	}

	public UserCredential getUserCredentialObject(Long idUser)
	{
		return userManager.getUserCredentialObject(idUser);
	}

	public Integer createForgetPassword(Long idUser, UserDto userDto, String saltKey) throws InvalidKeyException, NoSuchAlgorithmException
	{
		return userManager.createForgetPassword(idUser, userDto, saltKey);
	}

	public UserCredential createUserCredentialObject(Long idUser, String password) throws InvalidKeyException, NoSuchAlgorithmException
	{
		return userManager.createUserCredentialObject(idUser, password);
	}

	public Password createPasswordEntry(String encryptText, String email, Long idUser, String saltKey)
	{
		return userManager.createPasswordEntry(encryptText, email, idUser, saltKey);
	}

	public Password getPasswordObject(Integer idPassword)
	{
		return userManager.getPasswordObject(idPassword);
	}

	public List<Password> expireLinks(Long idUser)
	{
		return userManager.expireLinks(idUser);
	}

	public boolean verifyPreviousPasswords(Long idUser, String newPassword) throws InvalidKeyException, NoSuchAlgorithmException
	{
		CrackersLogger.info(logger, "Inside verifyPreviousPasswords" + idUser);
		return userManager.verifyPreviousPasswords(idUser, newPassword);
	}

	public User checkValidUser(Long idUser)
	{
		CrackersLogger.info(logger, "Inside checkValidUser" + idUser);
		return userManager.checkValidUser(idUser);
	}

	public Password getPasswordExpiredObject(Integer idPassword)
	{
		return userManager.getPasswordExpiredObject(idPassword);
	}
}