package com.crackers.manager.db;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CommonConstants;
import com.crackers.common.CrackersLogger;
import com.crackers.common.DateStringUtil;
import com.crackers.dto.ImageDto;
import com.crackers.dto.PhoneTypeDto;
import com.crackers.dto.UserDto;
import com.crackers.model.ContactDetails;
import com.crackers.model.Email;
import com.crackers.model.Image;
import com.crackers.model.ImageColorCode;
import com.crackers.model.Password;
import com.crackers.model.PhoneNumber;
import com.crackers.model.Role;
import com.crackers.model.User;
import com.crackers.model.UserCredential;
import com.crackers.model.UserSource;
import com.crackers.model.UserState;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.repositories.CredentialRepository;
import com.crackers.repositories.EmailRepository;
import com.crackers.repositories.ImageColorCodeRepository;
import com.crackers.repositories.ImageRepository;
import com.crackers.repositories.PasswordRepository;
import com.crackers.repositories.PhoneNumberRepository;
import com.crackers.repositories.PhoneTypeRepository;
import com.crackers.repositories.RoleRepository;
import com.crackers.repositories.UserContactDetailsRepository;
import com.crackers.repositories.UserRepository;
import com.crackers.repositories.UserSourceRepository;
import com.crackers.repositories.UserStateRepository;
import com.crackers.translators.ImageTranslator;
import com.crackers.translators.PersonTranslator;
import com.crackers.translators.PhoneTypeTranslator;
import com.crackers.translators.RoleTranslator;
import com.crackers.translators.UserSourceTranslator;
import com.crackers.translators.UserStateTranslator;
import com.crackers.translators.UserTranslator;

@Component
public class UserManager
{

	private Logger							logger	= Logger.getLogger(UserManager.class);
	@Resource
	private UserRepository					userRepository;
	@Resource
	private PhoneNumberRepository			phoneNumberRepository;
	@Resource
	private UserTranslator					userTranslator;
	@Resource
	private EmailRepository					emailRepository;
	@Resource
	private ImageTranslator					imageTranslator;
	@Resource
	private ImageRepository					imageRepository;
	@Resource
	private PersonTranslator				personTranslator;
	@Resource
	private RoleRepository					roleRepository;
	@Resource
	private RoleTranslator					roleTranslator;
	@Resource
	private UserSourceRepository			userSourceRepository;
	@Resource
	private UserSourceTranslator			userSourceTranslator;
	@Resource
	private UserStateRepository				userStateRepository;
	@Resource
	private UserStateTranslator				userStateTranslator;
	@Resource
	private CredentialRepository			credentialRepository;
	@Resource
	private PasswordRepository				passwordRepository;
	@Resource
	private ImageColorCodeRepository		imageColorCodeRepository;
	@Resource
	private ApplicationConfigRepository		applicationConfigRepository;
	@Resource
	private UserContactDetailsRepository	userContactDetailsRepository;
	@Resource
	private PhoneTypeTranslator				phoneTypeTranslator;
	@Resource
	private PhoneTypeRepository				phoneTypeRepository;

	public com.crackers.model.User getUser(Long idUser)
	{
		CrackersLogger.info(logger, "Starts fetching of getUserList");
		com.crackers.model.User info = userRepository.validateById(idUser);
		if (info == null)
		{
			return null;
		}
		return info;
	}

	public User createUser(Long idUser, User user)
	{
		Integer random = (int) (Math.random() * 17 + 1);
		ImageColorCode imageColorCode = imageColorCodeRepository.findOne(random.longValue());
		Long ts = DateStringUtil.getCurrentLong();
		user.setIsDeleted((short) 0);
		user.setIdUserState(1);
		if (user.getIdImageColorCode() == null && imageColorCode != null)
		{
			user.setIdImageColorCode(imageColorCode.getId());
		}
		user.setCreatedBy(idUser);
		user.setCreatedOn(ts);
		user.setLastUpdatedOn(ts);
		return userRepository.save(user);
	}

	public Long getUser(String userName)
	{
		return userRepository.getUser(userName);
	}

	public UserDto getUserMasterDto(UserDto userDto) throws InvocationTargetException
	{
		if (userDto.getRoleDto() != null && userDto.getRoleDto().getIdRole() != null)
		{
			Role role = roleRepository.findOne(userDto.getRoleDto().getIdRole().longValue());
			userDto.setRoleDto(roleTranslator.translateToRoleDto(role));
		}
		if (userDto.getUserSourceDto() != null && userDto.getUserSourceDto().getIdSource() != null)
		{
			UserSource userSource = userSourceRepository.findOne(userDto.getUserSourceDto().getIdSource().longValue());
			userDto.setUserSourceDto(userSourceTranslator.translateToUserSourceDto(userSource));
		}
		if (userDto.getUserStateDto() != null && userDto.getUserStateDto().getIdUserState() != null)
		{
			UserState userState = userStateRepository.findOne(userDto.getUserStateDto().getIdUserState().longValue());
			userDto.setUserStateDto(userStateTranslator.translateToUserStateDto(userState));
		}
		return userDto;
	}

	public UserDto updateUserImageDetails(Long idUser, ImageDto imageDto, Long idCurrentUser) throws InvocationTargetException, IOException
	{
		CrackersLogger.info(logger, "Id User:" + idUser);
		Long ts = DateStringUtil.getCurrentLong();
		Image entityImage = imageTranslator.translateDtoToImage(imageDto);
		Image image = imageRepository.getImageById(idUser);
		Image created;
		UserDto userDto;
		if (image != null)
		{
			CrackersLogger.info(logger, "The record already present");
			BeanUtil.copyBeanProperties(entityImage, image, new ArrayList<>());
			image.setUpdatedBy(idUser);
			image.setUpdatedOn(ts);
			created = imageRepository.save(image);
			User user = userRepository.findOne(idUser.longValue());
			user.setUpdatedBy(idCurrentUser);
			user.setUpdatedOn(ts);
			userDto = userTranslator.translateToUserDto(userRepository.save(user));
			userDto.setImageDto(imageTranslator.translateImageToDto(created));
		}
		else
		{
			CrackersLogger.info(logger, "The New Record");
			entityImage.setIsDeleted((short) 0);
			entityImage.setCreatedOn(ts);
			entityImage.setCreatedBy(idCurrentUser);
			created = imageRepository.save(entityImage);
			User user = userRepository.findOne(idUser.longValue());
			user.setUpdatedBy(idUser);
			user.setUpdatedOn(ts);
			userDto = userTranslator.translateToUserDto(userRepository.save(user));
			userDto.setImageDto(imageTranslator.translateImageToDto(created));
		}
		return userDto;
	}

	public List<PhoneNumber> getUserPhoneNumbers(Long idUser)
	{
		return phoneNumberRepository.getUserPhoneNumbers(idUser);
	}

	public PhoneNumber updatePhoneNumber(Long idUser, PhoneNumber phoneNumbergiven, Long idCurrentUser) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "PhoneNumber already exist");
		PhoneNumber phoneNumber = phoneNumberRepository.findOne(phoneNumbergiven.getId().longValue());
		BeanUtil.copyBeanProperties(phoneNumbergiven, phoneNumber, new ArrayList<>());
		Long ts = DateStringUtil.getCurrentLong();
		phoneNumber.setUpdatedBy(idUser);
		phoneNumber.setUpdatedOn(ts);
		return phoneNumberRepository.save(phoneNumber);
	}

	public PhoneNumber createPhoneNumber(Long idUser, PhoneNumber phoneNumbergiven, Long idCurrentUser)
	{
		CrackersLogger.info(logger, "PhoneNumber is New for this user");
		Long ts = DateStringUtil.getCurrentLong();
		phoneNumbergiven.setCreatedBy(idCurrentUser);
		phoneNumbergiven.setCreatedOn(ts);
		phoneNumbergiven.setIsDeleted((short) 0);
		return phoneNumberRepository.save(phoneNumbergiven);
	}

	public List<Email> getUserEmails(Long idUser)
	{
		return emailRepository.getUsersMailId(idUser);
	}

	public Email updateEmail(Long idUser, Email email, Long idCurrentUser) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Email already exist");
		Email mail = emailRepository.findOne(email.getId().longValue());
		Long ts = DateStringUtil.getCurrentLong();
		if (email != null && email.getIsPrimary() != null && !(email.getIsPrimary().equals(mail.getIsPrimary())) && !(mail.getEmailSource().equals(com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM))))
		{
			CrackersLogger.info(logger, "This is not FORM mail id");
			email.setIsPrimary(mail.getIsPrimary());
		}
		else if (email != null && email.getIsPrimary() != null && !(email.getIsPrimary().equals(mail.getIsPrimary())) && email.getIsPrimary() == 1 && mail.getEmailSource().equals(com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM)))
		{
			CrackersLogger.info(logger, "This is FORM mail id");
			Email primaryMail = emailRepository.getCreatedByMailId(idUser);
			if (primaryMail != null)
			{
				if (primaryMail.getEmailSource().equals(com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM)))
				{
					CrackersLogger.info(logger, "Primary mail for this user from FORM only");
					primaryMail.setIsPrimary((short) 0);
					primaryMail.setUpdatedBy(idCurrentUser);
					primaryMail.setUpdatedOn(ts);
					emailRepository.save(primaryMail);
				}
				else
				{
					CrackersLogger.info(logger, "Primary mail for this user from LDAP/AD, So can't enter this mail as primary.");
					email.setIsPrimary((short) 0);
				}
			}
		}
		if (email != null && email.getEmailValue() != null && !(email.getEmailValue().equals(mail.getEmailValue())) && mail.getEmailSource() != null && !(mail.getEmailSource().equals(com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM)))
				&& email.getEmailSource().equals(mail.getEmailSource()))
		{
			CrackersLogger.info(logger, "Mail id cant change");
			email.setEmailValue(mail.getEmailValue());
		}
		if (email != null && email.getEmailValue() != null && !(email.getEmailValue().equals(mail.getEmailValue())) && mail.getEmailSource() != null && mail.getEmailSource().equals(com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM))
				&& email.getEmailSource().equals(mail.getEmailSource()))
		{
			CrackersLogger.info(logger, "Mail id Changed");
			User user = userRepository.findOne(idUser.longValue());
			user.setUserName(email.getEmailValue());
			user.setUpdatedBy(idCurrentUser);
			user.setUpdatedOn(ts);
			user.setLastUpdatedOn(ts);
			userRepository.save(user);
		}
		BeanUtil.copyBeanProperties(email, mail, new ArrayList<>());
		mail.setUpdatedBy(idCurrentUser);
		mail.setUpdatedOn(ts);
		return emailRepository.save(mail);
	}

	public Email createEmail(Long idUser, Email email, Long idCurrentUser)
	{
		Long ts = DateStringUtil.getCurrentLong();
		if (email != null && email.getIsPrimary() != null && !(email.getIsPrimary().equals(1)))
		{
			CrackersLogger.info(logger, "Given mail id is primary");
			Email primaryMail = emailRepository.getCreatedByMailId(idUser);
			if (primaryMail != null)
			{
				if (primaryMail.getEmailSource().equals(com.crackers.enums.UserSource.getCode(com.crackers.enums.UserSource.FORM)))
				{
					primaryMail.setIsPrimary((short) 0);
					primaryMail.setUpdatedBy(idCurrentUser);
					primaryMail.setUpdatedOn(ts);
					emailRepository.save(primaryMail);
				}
				else
				{
					email.setIsPrimary((short) 0);
				}
			}
		}
		CrackersLogger.info(logger, "PhoneNumber is New for this user");
		email.setCreatedBy(idCurrentUser);
		email.setCreatedOn(ts);
		email.setIsDeleted((short) 0);
		return emailRepository.save(email);
	}

	public User updateUser(Long idUser, Long idCurrentUser, User user) throws InvocationTargetException
	{
		User oldUser = userRepository.findOne(idUser.longValue());
		Long ts = DateStringUtil.getCurrentLong();
		BeanUtil.copyBeanProperties(user, oldUser, new ArrayList<>());
		oldUser.setUpdatedBy(idCurrentUser);
		oldUser.setUpdatedOn(ts);
		return userRepository.save(oldUser);
	}

	public List<ContactDetails> getUserContactDetails(Long idUser)
	{
		return userContactDetailsRepository.getContactDetails(idUser);
	}

	public ContactDetails updateContactDetails(Long idUser, ContactDetails contactDetails, Long idCurrentUser) throws InvocationTargetException
	{
		CrackersLogger.info(logger, "Starts  updateIndividualAddressDetails");
		logger.debug("Calling :" + "updateUserContactDetails" + "(" + idUser + "," + contactDetails + "," + idCurrentUser + ")");
		ContactDetails contactDetailsgiven = userContactDetailsRepository.findOne(contactDetails.getId().longValue());
		CrackersLogger.info(logger, "*****************" + contactDetails);
		CrackersLogger.info(logger, "The record already present");
		BeanUtil.copyBeanProperties(contactDetails, contactDetailsgiven, new ArrayList<>());
		contactDetailsgiven.setUpdatedBy(idCurrentUser);
		Long ts = DateStringUtil.getCurrentLong();
		contactDetailsgiven.setUpdatedOn(ts);
		ContactDetails updated = userContactDetailsRepository.save(contactDetailsgiven);
		User individualEntity = userRepository.findOne(idUser.longValue());
		individualEntity.setUpdatedBy(idUser);
		individualEntity.setUpdatedOn(new Long((new Date()).getTime()));
		userRepository.save(individualEntity);
		return updated;
	}

	public ContactDetails createContactDetails(Long idUser, ContactDetails contactDetails, Long idCurrentUser) throws InvocationTargetException
	{
		ContactDetails entityContactDetails = new ContactDetails();
		Long ts = DateStringUtil.getCurrentLong();
		entityContactDetails.setIsDeleted((short) 0);
		entityContactDetails.setCreatedOn(ts);
		entityContactDetails.setCreatedBy(idCurrentUser);
		CrackersLogger.info(logger, "individual entity id:" + idUser);
		BeanUtil.copyBeanProperties(contactDetails, entityContactDetails, new ArrayList<>());
		ContactDetails created = userContactDetailsRepository.save(entityContactDetails);
		User individualEntity = userRepository.findOne(idUser.longValue());
		individualEntity.setUpdatedBy(idUser);
		individualEntity.setUpdatedOn(new Long((new Date()).getTime()));
		userRepository.save(individualEntity);
		return created;
	}

	public Long getUserCount(String uniqueId, Integer idUserState, String keyword, Integer isExact)
	{
		return userRepository.getUserCount(idUserState);
	}

	public Long validateNewUserMailId(String emailId)
	{
		return emailRepository.validateNewUserMailId(emailId);
	}

	public String getUserCredential(Long idUser)
	{
		return credentialRepository.getCredentialByUserId((long) idUser);
	}

	public UserCredential getUserCredentialObject(Long idUser)
	{
		return credentialRepository.getCredentialObject((long) idUser);
	}

	public synchronized Integer createForgetPassword(Long idUser, UserDto userDto, String saltKey) throws InvalidKeyException, NoSuchAlgorithmException
	{
		String hashedKey = encryptPassword(userDto.getNewPassword(), saltKey, CommonConstants.ENCRYPTION_ALGORITHM);
		UserCredential userCredential = credentialRepository.getCredentialObject(idUser.longValue());
		Long ts = DateStringUtil.getCurrentLong();
		if (userCredential != null)
		{
			userCredential.setIdUser(idUser);
			userCredential.setSaltKey(saltKey);
			userCredential.setIsDeleted((short) 0);
			userCredential.setHashedKey(hashedKey);
			userCredential.setUpdatedBy(idUser);
			userCredential.setUpdatedOn(ts);
			userCredential = credentialRepository.save(userCredential);
		}
		else
		{
			CrackersLogger.info(logger, "User Credential new");
			userCredential = new UserCredential();
			userCredential.setIdUser(idUser);
			userCredential.setSaltKey(saltKey);
			userCredential.setIsDeleted((short) 0);
			userCredential.setHashedKey(hashedKey);
			userCredential.setCreatedBy(idUser);
			userCredential.setCreatedOn(ts);
			userCredential.setUpdatedBy(idUser);
			userCredential.setUpdatedOn(ts);
			userCredential = credentialRepository.save(userCredential);
		}
		return Integer.valueOf(userCredential.getIdUser().intValue());
	}

	// Private method for encrypt the password with salt key, and generate the Hashed key for authentication.
	private static String encryptPassword(String data, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException
	{
		/*
		 * It hold the secretKey Specifications of this encryption .
		 */
		SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(signingKey);
		byte[] rawSig = mac.doFinal(data.getBytes());
		return new String(Hex.encodeHex(rawSig));
	}

	public synchronized UserCredential createUserCredentialObject(Long idUser, String password) throws InvalidKeyException, NoSuchAlgorithmException
	{
		String hashedKey = encryptPassword(password, CommonConstants.SALT_KEY, CommonConstants.ENCRYPTION_ALGORITHM);
		UserCredential userCredential = new UserCredential();
		Long ts = DateStringUtil.getCurrentLong();
		userCredential.setIdUser(idUser);
		userCredential.setSaltKey(CommonConstants.SALT_KEY);
		userCredential.setIsDeleted((short) 0);
		userCredential.setHashedKey(hashedKey);
		userCredential.setCreatedBy(idUser);
		userCredential.setCreatedOn(ts);
		return credentialRepository.save(userCredential);
	}

	public synchronized Password createPasswordEntry(String encryptText, String email, Long idUser, String saltKey)
	{
		Password password = new Password();
		Long ts = DateStringUtil.getCurrentLong();
		password.setEncryptText(encryptText);
		password.setEmail(email);
		password.setIdUser(idUser);
		password.setSaltKey(saltKey);
		password.setIsDeleted((short) 0);
		password.setIsExpired((short) 0);
		password.setCreatedBy(idUser);
		password.setCreatedOn(ts);
		return passwordRepository.save(password);
	}

	public Password getPasswordObject(Integer idPassword)
	{
		return passwordRepository.getPasswordById(idPassword);
	}

	public boolean verifyPreviousPasswords(Long idUser, String newPassword) throws InvalidKeyException, NoSuchAlgorithmException
	{
		/*List<UserCredentialAud> userCredentialAud = userCredentialAudRepository.verifyPreviousPasswords(idUser);
		CrackersLogger.info(logger, "userCredentialAud.size()" + userCredentialAud.size());
		Integer count = 0;
		boolean flag = true;
		String configValue = applicationConfigRepository.getConfigValueByKey(CommonConstants.PREVIOUS_PASSWORD_CHECK_COUNT);
		String hashedPassword = encryptPassword(newPassword, CommonConstants.SALT_KEY, CommonConstants.ENCRYPTION_ALGORITHM);
		Iterator<UserCredentialAud> iterator = userCredentialAud.iterator();
		if (userCredentialAud == null || userCredentialAud.size() == 0)
		{
			return flag;
		}
		while (iterator.hasNext() && count.intValue() < Integer.valueOf(configValue).intValue())
		{
			CrackersLogger.info(logger, "Inside count check");
			UserCredentialAud password = iterator.next();
			if (hashedPassword.equals(password.getHashedKey()))
			{
				CrackersLogger.info(logger, "Passwords Are equal and setting value to false");
				flag = false;
				break;
			}
			count++;
		}
		return flag;*/
		return true;
	}

	public List<Password> expireLinks(Long idUser)
	{
		List<Password> passwords = passwordRepository.getPasswordListById(idUser);
		List<Password> passwordsFinal = new ArrayList<>();
		Iterator<Password> iterator = passwords.iterator();
		while (iterator.hasNext())
		{
			Password password = iterator.next();
			password.setIsExpired((short) 1);
			passwordsFinal.add(passwordRepository.save(password));
		}
		return passwordsFinal;
	}

	public User checkValidUser(Long idUser)
	{
		return userRepository.checkValidUser(idUser);
	}

	public Password getPasswordExpiredObject(Integer idPassword)
	{
		return passwordRepository.getPasswordExpiredObject(idPassword);
	}

	public PhoneTypeDto getPhoneType(Integer idPhoneType) throws InvocationTargetException
	{
		return phoneTypeTranslator.translateToPhoneTypeDto(phoneTypeRepository.findOne(idPhoneType.longValue()));
	}
}