package com.crackers.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.chain.user.Handler;
import com.crackers.chain.user.UserAddressHandler;
import com.crackers.chain.user.UserCredentialHandler;
import com.crackers.chain.user.UserEmailHandler;
import com.crackers.chain.user.UserHandler;
import com.crackers.chain.user.UserImageHandler;
import com.crackers.chain.user.UserPhoneHandler;
import com.crackers.common.CrackersLogger;
import com.crackers.dto.ContactDetailsDto;
import com.crackers.dto.EmailDto;
import com.crackers.dto.PhoneNumberDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.RegistrationException;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.model.ContactDetails;
import com.crackers.model.Email;
import com.crackers.model.PhoneNumber;
import com.crackers.model.User;
import com.crackers.model.UserCredential;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.repositories.ImageColorCodeRepository;
import com.crackers.translators.ContactDetailsTranslator;
import com.crackers.translators.EmailTranslator;
import com.crackers.translators.PersonTranslator;
import com.crackers.translators.PhoneNumberTranslator;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserService {

	private Logger						logger	= Logger.getLogger(UserService.class);
	@Resource
	private UserManager					userManager;
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
	private UserCredentialHandler		userCredentialHandler;
	@Resource
	private ImageColorCodeRepository	imageColorCodeRepository;
	@Resource
	private ApplicationConfigRepository	applicationConfigRepository;
	@Resource
	private PersonTranslator			personTranslator;
	@Resource
	private EmailTranslator				emailTranslator;
	@Resource
	private PhoneNumberTranslator		phoneNumberTranslator;
	@Resource
	private ContactDetailsTranslator	contactDetailsTranslator;

	public Long validateNewUserMailId(String emailId) {
		return userManager.validateNewUserMailId(emailId);
	}

	public String getUserCredential(Long idUser) {
		return userManager.getUserCredential(idUser);
	}

	public boolean verifyPreviousPasswords(Long idUser, String newPassword) throws InvalidKeyException, NoSuchAlgorithmException {
		CrackersLogger.info(logger, "Inside verifyPreviousPasswords" + idUser);
		return userManager.verifyPreviousPasswords(idUser, newPassword);
	}

	public UserCredential getUserCredentialObject(Long idUser) {
		return userManager.getUserCredentialObject(idUser);
	}

	public Long createForgetPassword(Long idUser, UserDto userDto, String saltKey) throws InvalidKeyException, NoSuchAlgorithmException {
		return userManager.createForgetPassword(idUser, userDto, saltKey);
	}

	public UserDto getUserList(Long idUser) throws RegistrationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService getUserList method");
		com.crackers.model.User user = userManager.getUser(idUser);
		if (user == null) {
			return null;
		}
		UserDto userDto = personTranslator.translateUserToDto(user);
		if (userDto.getIdImageColorCode() != null) {
			userDto.setImageColorCode(imageColorCodeRepository.getImageColorCode(userDto.getIdImageColorCode()));
		}
		userManager.getUserMasterDto(userDto);
		return userDto;
	}

	public Long getUser(String userName) {
		return userManager.getUser(userName);
	}

	public UserDto createUser(Long idUser, UserDto userDto) throws InvocationTargetException {
		User user = personTranslator.translateToPerson(userDto);
		User createdUser = userManager.createUser(idUser, user);
		UserDto cDto = personTranslator.translateUserToDto(createdUser);
		UserDto dto = userManager.getUserMasterDto(cDto);
		dto.setImageColorCode(imageColorCodeRepository.getImageColorCode(dto.getIdImageColorCode()));
		return dto;
	}

	public UserDto updateUser(Long idUser, UserDto userDto) throws InvocationTargetException, UnparseableDateTimeStringException, IOException {
		CrackersLogger.info(logger, "Calling UserService updateUser method " + userDto.getChangedList());
		Handler h1 = userHandler;
		Handler h2 = userImageHandler;
		Handler h3 = userEmailHandler;
		Handler h4 = userPhoneHandler;
		Handler h5 = userAddressHandler;
		Handler h6 = userCredentialHandler;
		h1.setSucessor(h2);
		h2.setSucessor(h3);
		h3.setSucessor(h4);
		h4.setSucessor(h5);
		h5.setSucessor(h6);
		Iterator<String> changedIterator = userDto.getChangedList().iterator();
		UserDto dto = new UserDto();
		while (changedIterator.hasNext()) {
			String changedList = changedIterator.next();
			CrackersLogger.info(logger, h1 + "Going to call Handler");
			dto = h1.handleRequest(CryptoBinderUtil.getDecryptId(userDto.getId()), userDto, changedList);
		}
		return dto;
	}

	public UserDto updateUserDetails(Long idUser, UserDto userDto) throws InvocationTargetException {
		User user = personTranslator.translateToPerson(userDto);
		User createdUser = userManager.updateUser(idUser, user);
		UserDto cDto = personTranslator.translateUserToDto(createdUser);
		UserDto dto = userManager.getUserMasterDto(cDto);
		CrackersLogger.info(logger, "Translted UserDto is : " + dto.getUserName());
		return dto;
	}

	public EmailDto createEmail(Long idUser, EmailDto emailDto) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService's:" + "createEmail" + "(" + idUser + "," +")");
		Email email = emailTranslator.translateDtoToEmail(emailDto);
		Email updatedPhoneNumber = userManager.createEmail(idUser, email);
		return emailTranslator.translateToEmailDto(updatedPhoneNumber);
	}

	public EmailDto updateUserEmail(Long idUser, EmailDto emailDto) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService updateUserEmail method");
		Email email = emailTranslator.translateDtoToEmail(emailDto);
		Email updatedEmail = userManager.updateUserEmail(idUser, email);
		return emailTranslator.translateToEmailDto(updatedEmail);
	}

	public List<EmailDto> getEmailList(Long idUser) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService's:" + "getEmailList" + "(" + idUser + ")");
		List<Email> emails = userManager.getUserEmails(idUser);
		return emailTranslator.translateListToEmailDto(emails);
	}

	public PhoneNumberDto createPhoneNumber(Long idUser, PhoneNumberDto phoneNumberDto) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService's:" + "createPhoneNumber" + "(" + idUser + "," + ")");
		PhoneNumber phoneNumber = phoneNumberTranslator.translateDtoToPhoneNumber(phoneNumberDto);
		PhoneNumber updatedPhoneNumber = userManager.createPhoneNumber(idUser, phoneNumber);
		return phoneNumberTranslator.translateToPhoneNumberDto(updatedPhoneNumber);
	}

	public PhoneNumberDto updateUserPhoneNumber(Long idUser, PhoneNumberDto phoneNumberDto) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService updateUserPhoneNumber method");
		PhoneNumber phoneNumber = phoneNumberTranslator.translateDtoToPhoneNumber(phoneNumberDto);
		PhoneNumber updatedPhoneNumber = userManager.updateUserPhoneNumber(idUser, phoneNumber);
		return phoneNumberTranslator.translateToPhoneNumberDto(updatedPhoneNumber);
	}

	public List<PhoneNumberDto> getPhoneNumberList(Long idUser) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService's:" + "getPhoneNumberList" + "(" + idUser + ")");
		List<PhoneNumber> phoneNumbers = userManager.getUserPhoneNumbers(idUser);
		return phoneNumberTranslator.translateListToPhoneNumberDto(phoneNumbers);
	}

	public ContactDetailsDto createContactDetails(Long idUser, ContactDetailsDto contactDetailsDto) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService's:" + "createContactDetails" + "(" + idUser + "," + ")");
		ContactDetails contactDetails = contactDetailsTranslator.translateToContactDetails(contactDetailsDto);
		ContactDetails updatedContactDetails = userManager.createContactDetails(idUser, contactDetails);
		return contactDetailsTranslator.translateToContactDetailsDto(updatedContactDetails);
	}

	public ContactDetailsDto updateUserContactDetails(Long idUser, ContactDetailsDto contactDetailsDto) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService updateUserContactDetails method");
		ContactDetails contactDetails = contactDetailsTranslator.translateToContactDetails(contactDetailsDto);
		ContactDetails updatedContactDetails = userManager.updateUserContactDetails(idUser, contactDetails);
		return contactDetailsTranslator.translateToContactDetailsDto(updatedContactDetails);
	}

	public List<ContactDetailsDto> getContactDetailsList(Long idUser) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService's:" + "getContactDetailsList" + "(" + idUser + ")");
		List<ContactDetails> contactDetailsList = userManager.getUserContactDetails(idUser);
		return contactDetailsTranslator.translateToDto(contactDetailsList);
	}
	
	public UserDto updateUserCredential(Long idUser, UserDto userDto) throws InvocationTargetException {
		CrackersLogger.info(logger, "Calling UserService's:" + "updateUserCredential" + "(" + idUser + "," + ")");
		return userManager.updateUserCredential(idUser, userDto);
	}
}