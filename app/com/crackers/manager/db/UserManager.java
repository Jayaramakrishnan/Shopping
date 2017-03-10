package com.crackers.manager.db;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CommonConstants;
import com.crackers.common.CrackersLogger;
import com.crackers.common.DateStringUtil;
import com.crackers.controllers.login.Dashboard;
import com.crackers.dto.UserDto;
import com.crackers.model.ContactDetails;
import com.crackers.model.Email;
import com.crackers.model.ImageColorCode;
import com.crackers.model.PhoneNumber;
import com.crackers.model.Role;
import com.crackers.model.User;
import com.crackers.model.UserCredential;
import com.crackers.model.UserCredentialAud;
import com.crackers.repositories.CredentialRepository;
import com.crackers.repositories.EmailRepository;
import com.crackers.repositories.ImageColorCodeRepository;
import com.crackers.repositories.PhoneNumberRepository;
import com.crackers.repositories.RoleRepository;
import com.crackers.repositories.UserContactDetailsRepository;
import com.crackers.repositories.UserCredentialAudRepository;
import com.crackers.repositories.UserRepository;
import com.crackers.translators.RoleTranslator;
import com.crackers.util.PassEncryptUtil;

@Component
public class UserManager {

	private Logger							logger	= Logger.getLogger(UserManager.class);
	@Resource
	private UserRepository					userRepository;
	@Resource
	private EmailRepository					emailRepository;
	@Resource
	private CredentialRepository			credentialRepository;
	@Resource
	private PassEncryptUtil					passEncryptUtil;
	@Resource
	private RoleRepository					roleRepository;
	@Resource
	private RoleTranslator					roleTranslator;
	@Resource
	private ImageColorCodeRepository		imageColorCodeRepository;
	@Resource
	private UserCredentialAudRepository		userCredentialAudRepository;
	@Resource
	private PhoneNumberRepository			phoneNumberRepository;
	@Resource
	private UserContactDetailsRepository	contactDetailsRepository;

	public Long validateNewUserMailId(String emailId) {
		return emailRepository.validateNewUserMailId(emailId);
	}

	public String getUserCredential(Long idUser) {
		return credentialRepository.getCredentialByUserId(idUser);
	}

	public UserCredential getUserCredentialObject(Long idUser) {
		return credentialRepository.getCredentialObject(idUser);
	}

	public boolean verifyPreviousPasswords(Long idUser, String newPassword) throws InvalidKeyException, NoSuchAlgorithmException {
		List<UserCredentialAud> userCredentialAud = userCredentialAudRepository.verifyPreviousPasswords(idUser);
		CrackersLogger.info(logger, "Previous Passwords size " + userCredentialAud.size());
		Integer count = 0;
		boolean flag = true;
		Integer configValue = Dashboard.clientConfigurationSettings.getPreviousPasswordCheckCount();
		String hashedPassword = passEncryptUtil.encryptPassword(newPassword, CommonConstants.SALT_KEY, CommonConstants.ENCRYPTION_ALGORITHM);
		Iterator<UserCredentialAud> iterator = userCredentialAud.iterator();
		if (userCredentialAud == null || userCredentialAud.size() == 0) {
			return flag;
		}
		while (iterator.hasNext() && count.intValue() < configValue.intValue()) {
			CrackersLogger.info(logger, "Inside count check");
			UserCredentialAud password = iterator.next();
			if (hashedPassword.equals(password.getHashedKey())) {
				CrackersLogger.info(logger, "Passwords Are equal and setting value to false");
				flag = false;
				break;
			}
			count++;
		}
		return flag;
	}

	public synchronized Long createForgetPassword(Long idUser, UserDto userDto, String saltKey) throws InvalidKeyException, NoSuchAlgorithmException {
		String hashedKey = passEncryptUtil.encryptPassword(userDto.getNewPassword(), saltKey, CommonConstants.ENCRYPTION_ALGORITHM);
		UserCredential userCredential = credentialRepository.getCredentialObject(idUser);
		Long ts = DateStringUtil.getCurrentTimestamp();
		if (userCredential == null) {
			CrackersLogger.info(logger, "User Credential new");
			userCredential = new UserCredential();
			userCredential.setCreatedBy(idUser);
			userCredential.setCreatedOn(ts);
		}
		userCredential.setIdUser(idUser);
		userCredential.setSaltKey(saltKey);
		userCredential.setIsDeleted((short) 0);
		userCredential.setHashedKey(hashedKey);
		userCredential.setUpdatedBy(idUser);
		userCredential.setUpdatedOn(ts);
		userCredential = credentialRepository.save(userCredential);
		return userCredential.getIdUser();
	}

	public com.crackers.model.User getUser(Long idUser) {
		CrackersLogger.info(logger, "Starts fetching of getUserList");
		com.crackers.model.User info = userRepository.validateById(idUser);
		if (info == null) {
			return null;
		}
		return info;
	}

	public UserDto getUserMasterDto(UserDto userDto) throws InvocationTargetException {
		if (userDto.getRoleDto() != null && userDto.getRoleDto().getIdRole() != null) {
			Role role = roleRepository.findOne(userDto.getRoleDto().getIdRole().longValue());
			userDto.setRoleDto(roleTranslator.translateToRoleDto(role));
		}
		return userDto;
	}

	public Long getUser(String userName) {
		return userRepository.getUser(userName);
	}

	public User createUser(Long idUser, User user) {
		Integer random = (int) (Math.random() * 17 + 1);
		ImageColorCode imageColorCode = imageColorCodeRepository.findOne(random.longValue());
		Long ts = DateStringUtil.getCurrentTimestamp();
		user.setIsDeleted((short) 0);
		if (user.getIdImageColorCode() == null && imageColorCode != null) {
			user.setIdImageColorCode(imageColorCode.getId());
		}
		user.setCreatedBy(idUser);
		user.setCreatedOn(ts);
		return userRepository.save(user);
	}

	public User updateUser(Long idUser, User user) throws InvocationTargetException {
		User oldUser = userRepository.findOne(idUser.longValue());
		Long ts = DateStringUtil.getCurrentTimestamp();
		BeanUtil.copyBeanProperties(user, oldUser, new ArrayList<>());
		oldUser.setUpdatedBy(idUser);
		oldUser.setUpdatedOn(ts);
		return userRepository.save(oldUser);
	}

	public Email createEmail(Long idUser, Email email) {
		Long ts = DateStringUtil.getCurrentTimestamp();
		CrackersLogger.info(logger, "Given mail id is primary");
		email.setCreatedBy(idUser);
		email.setCreatedOn(ts);
		email.setIsDeleted((short) 0);
		emailRepository.save(email);
		User user = userRepository.checkValidUser(idUser);
		user.setEmail(email);
		userRepository.save(user);
		return email;
	}

	public Email updateUserEmail(Long idUser, Email email) throws InvocationTargetException {
		CrackersLogger.info(logger, "Email already exist");
		Email mail = emailRepository.findOne(email.getId());
		Long ts = DateStringUtil.getCurrentTimestamp();
		if (!email.getEmail().equalsIgnoreCase(mail.getEmail()) && email.getIsPrimary() == 1) {
			User user = userRepository.findOne(idUser);
			user.setUserName(email.getEmail());
			user.setUpdatedBy(idUser);
			user.setUpdatedOn(ts);
			userRepository.save(user);
		}
		BeanUtil.copyBeanProperties(email, mail, new ArrayList<>());
		mail.setUpdatedBy(idUser);
		mail.setUpdatedOn(ts);
		return emailRepository.save(mail);
	}

	public List<Email> getUserEmails(Long idUser) {
		return emailRepository.getUsersMailId(idUser);
	}

	public PhoneNumber createPhoneNumber(Long idUser, PhoneNumber phoneNumber) {
		Long ts = DateStringUtil.getCurrentTimestamp();
		phoneNumber.setCreatedBy(idUser);
		phoneNumber.setCreatedOn(ts);
		phoneNumber.setIsDeleted((short) 0);
		phoneNumberRepository.save(phoneNumber);
		User user = userRepository.checkValidUser(idUser);
		user.setPhoneNumber(phoneNumber);
		userRepository.save(user);
		return phoneNumber;
	}

	public PhoneNumber updateUserPhoneNumber(Long idUser, PhoneNumber phoneNumber) throws InvocationTargetException {
		CrackersLogger.info(logger, "PhoneNumber already exist");
		PhoneNumber phNo = phoneNumberRepository.findOne(phoneNumber.getId());
		Long ts = DateStringUtil.getCurrentTimestamp();
		BeanUtil.copyBeanProperties(phoneNumber, phNo, new ArrayList<>());
		phNo.setUpdatedBy(idUser);
		phNo.setUpdatedOn(ts);
		return phoneNumberRepository.save(phNo);
	}

	public List<PhoneNumber> getUserPhoneNumbers(Long idUser) {
		return phoneNumberRepository.getUserPhoneNumbers(idUser);
	}

	public ContactDetails createContactDetails(Long idUser, ContactDetails contactDetails) {
		Long ts = DateStringUtil.getCurrentTimestamp();
		contactDetails.setCreatedBy(idUser);
		contactDetails.setCreatedOn(ts);
		contactDetails.setIsDeleted((short) 0);
		contactDetailsRepository.save(contactDetails);
		User user = userRepository.checkValidUser(idUser);
		user.setAddress(contactDetails);
		userRepository.save(user);
		return contactDetails;
	}

	public ContactDetails updateUserContactDetails(Long idUser, ContactDetails contactDetails) throws InvocationTargetException {
		CrackersLogger.info(logger, "ContactDetails already exist");
		ContactDetails contactDetail = contactDetailsRepository.findOne(contactDetails.getId());
		Long ts = DateStringUtil.getCurrentTimestamp();
		BeanUtil.copyBeanProperties(contactDetails, contactDetail, new ArrayList<>());
		contactDetail.setUpdatedBy(idUser);
		contactDetail.setUpdatedOn(ts);
		return contactDetailsRepository.save(contactDetail);
	}

	public List<ContactDetails> getUserContactDetails(Long idUser) {
		return contactDetailsRepository.getContactDetails(idUser);
	}

	public UserDto updateUserCredential(Long idUser, UserDto userDto) {
		UserCredential userCredential = credentialRepository.getCredentialObject(idUser);
		Long ts = DateStringUtil.getCurrentTimestamp();
		if (userCredential == null) {
			CrackersLogger.info(logger, "First Time Password Creation");
			userCredential = new UserCredential();
			userCredential.setIdUser(idUser);
			userCredential.setSaltKey(CommonConstants.SALT_KEY);
			String password = "";
			try {
				password = passEncryptUtil.encryptPassword(userDto.getNewPassword(), CommonConstants.SALT_KEY, CommonConstants.ENCRYPTION_ALGORITHM);
			}
			catch (InvalidKeyException | NoSuchAlgorithmException e) {
				CrackersLogger.error(logger, "Error while encrypting the password", e);
			}
			userCredential.setHashedKey(password);
			userCredential.setIsDeleted((short) 0);
			userCredential.setCreatedBy(idUser);
			userCredential.setCreatedOn(ts);
			credentialRepository.save(userCredential);
			User user = userRepository.checkValidUser(idUser);
			user.setPassword(userCredential);
			userRepository.save(user);
		}else{
			CrackersLogger.info(logger, "Change of Password");
			userCredential.setHashedKey(userDto.getNewPassword());
			userCredential.setUpdatedBy(idUser);
			userCredential.setUpdatedOn(ts);
			credentialRepository.save(userCredential);
		}
		CrackersLogger.info(logger, "Password is updated");
		return userDto;
	}
}