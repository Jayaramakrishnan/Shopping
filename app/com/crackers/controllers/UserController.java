package com.crackers.controllers;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.stereotype.Component;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.common.EmailHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.common.SendEMail;
import com.crackers.dto.EmailDto;
import com.crackers.dto.PasswordDto;
import com.crackers.dto.UserDto;
import com.crackers.dto.UserInfo;
import com.crackers.es.observers.UserSettingsElasticSearchIndexObserver;
import com.crackers.model.EmailTemplate;
import com.crackers.model.Password;
import com.crackers.model.User;
import com.crackers.model.UserCredential;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.repositories.EmailTemplateRepository;
import com.crackers.repositories.UserRepository;
import com.crackers.services.UserService;
import com.crackers.util.CacheManager;
import com.crackers.util.CryptoBinderUtil;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class UserController extends BaseController
{

    private static Logger                          logger = Logger.getLogger(UserController.class);
    @Resource
    private UserService                            userService;
    @Resource
    private SendEMail                              sendEMail;
    @Resource
    private ApplicationConfigRepository            applicationConfigRepository;
    @Resource
    private EmailTemplateRepository                emailTemplateRepository;
    @Resource
    private UserRepository                         userRepository;
    @Resource
    private UserSettingsElasticSearchIndexObserver userSettingsElasticSearchIndexObserver;

    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser(String uniqueId)
    {
        JsonNode json = request().body().asJson();
        UserDto userDto = Json.fromJson(json, UserDto.class);
        if (uniqueId == null || userDto == null || userDto.getUserName() == null || userDto.getUserName().equals(RestUrlAttribute.EMPTY_QUOTES))
        {
            return badRequest();
        }
        Integer idUser = CacheManager.getIdUserFromCache(uniqueId);
        if (idUser == null)
        {
            CMSLogger.error(logger, "Invalid UniqueId", null);
            return unauthorized();
        }
        if (userService.getUser(userDto.getUserName()) != 0)
        {
            return badRequest("User name all ready exist!!!");
        }
        UserDto userDtoCreated = new UserDto();
        List<String> changedList = new ArrayList<>();
        changedList.add("Image");
        changedList.add("phoneNumber");
        changedList.add("Email");
        changedList.add("address");
        userDto.setChangedList(changedList);
        CMSLogger.info(logger, "The logged user is:" + idUser + "\n" + "UserTitle for new user is:" + userDto.getUserName());
        try
        {
            CMSLogger.info(logger, "User Title is valid,Going to create new User.");
            if (userDto.getUserName() != RestUrlAttribute.EMPTY_QUOTES && userDto.getUserName() != null)
            {
                userDtoCreated = userService.createUser(idUser, userDto);
                if (userDtoCreated == null)
                {
                    CMSLogger.error(logger, "Problem while creating user", null);
                    return noContent();
                }
                else
                {
                    BeanUtil.copyBeanProperties(userDtoCreated, userDto, new ArrayList<>());
                    BeanUtil.copyBeanProperties(userService.updateUser(idUser, userDto), userDtoCreated, new ArrayList<>());
                    CMSLogger.info(logger, "User is Created Successfully");
                    List<EmailDto> emails = userDtoCreated.getEmailDtos();
                    Iterator<EmailDto> iterator = emails.iterator();
                    while (iterator.hasNext())
                    {
                        CMSLogger.info(logger, "Inside Email Iterator");
                        EmailDto emailDto = iterator.next();
                        if (emailDto.getIsPrimary().intValue() == 1)
                        {
                            CMSLogger.info(logger, "Email IsPrimary is 1");
                            Integer userId = userService.validateNewUserMailId(emailDto.getEmail());
                            List<Password> expireLinks = userService.expireLinks(userId);
                            CMSLogger.info(logger, "expireLinks List" + expireLinks);
                            String keyString = generateSecretKey();
                            String encryptedText = CryptoBinderUtil.getEncryptId(emailDto.getEmail());
                            CMSLogger.info(logger, "Encrypted Id" + encryptedText);
                            CMSLogger.info(logger, "Decrypted Id" + CryptoBinderUtil.getDecryptedString(encryptedText));
                            Password password = userService.createPasswordEntry(encryptedText, emailDto.getEmail(), userId, keyString);
                            String baseUrl = RestUrlAttribute.REST_BASE_URL;
                            if (baseUrl.endsWith("/"))
                            {
                                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
                            }
                            CMSLogger.info(logger, "baseUrl.substring(0, baseUrl.length());" + baseUrl);
                            String createPasswordUrl = baseUrl + com.crackers.controllers.web.routes.PasswordController.createPassword(password.getIdPassword(), password.getEncryptText()).url();
                            CMSLogger.info(logger, "createPasswordUrl" + createPasswordUrl);
                            URLEncoder.encode(createPasswordUrl, "UTF-8");
                            UserCredential userCredential = userService.getUserCredentialObject(userId);
                            if (userCredential == null)
                            {
                                Map<String, String> map = new HashMap<>();
                                EmailTemplate emailTemplate = new EmailTemplate();
                                if (userDtoCreated.getUserSourceDto() != null && userDtoCreated.getUserSourceDto().getIdSource().intValue() == 1)
                                {
                                    emailTemplate = emailTemplateRepository.findOne(CommonConstants.NEW_PASSWORD.longValue());
                                    map.put("[[New Password]]", createPasswordUrl);
                                }
                                map.put(CommonConstants.POWERED_BY_XXX, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.POWERED_BY_XXX)));
                                map.put(CommonConstants.SUPPORT_EMAIL_ID, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.SUPPORT_EMAIL_ID)));
                                emailTemplate.setBody(EmailHelper.getFilledEmailTemplate(map, emailTemplate.getBody()));
                                UserInfo userInfo = new UserInfo();
                                BeanUtil.copyBeanProperties(userDtoCreated, userInfo, new ArrayList<>());
                                userInfo.setIdUser(CryptoBinderUtil.getDecryptId(userDtoCreated.getIdUser()));
                                List<UserInfo> userInfos = new ArrayList<>();
                                userInfos.add(userInfo);
                                sendEMail.sendMail(emailTemplate, userId, userRepository.validateById(idUser), userInfos);
                            }
                        }
                    }
                }
            }
            userSettingsElasticSearchIndexObserver.update(userDtoCreated);
            return ok(Json.toJson(userDtoCreated));
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Exception while user Creation", e);
            return internalServerError();
        }
    }

    public String generateSecretKey() throws NoSuchAlgorithmException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(168);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.encode(secretKey.getEncoded());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result validateNewPassword()
    {
        JsonNode json = request().body().asJson();
        PasswordDto passwordDto = Json.fromJson(json, PasswordDto.class);
        if (passwordDto == null || passwordDto.getIdPassword() == null || passwordDto.getEncryptText() == null)
        {
            CMSLogger.error(logger, "Invalid inputs", null);
            return badRequest();
        }
        try
        {
            CMSLogger.info(logger, "Inside validateNewPassword");
            Password password = userService.getPasswordObject(passwordDto.getIdPassword());
            if (password != null)
            {
                String decryptText = CryptoBinderUtil.getDecryptedString(passwordDto.getEncryptText());
                User user = userService.checkValidUser(password.getIdUser());
                if (decryptText.equals(password.getEmail()) && user != null)
                {
                    CMSLogger.info(logger, "Returning ok if emails are equal");
                    return ok(Json.toJson(RestUrlAttribute.EMPTY_QUOTES));
                }
                else
                {
                    CMSLogger.info(logger, "Given mail id doesn't match with User's registered mail Id");
                    return badRequest("Given mail id is not registered one.");
                }
            }
            else
            {
                CMSLogger.info(logger, "Link Got Expired if password object is null");
                return unauthorized("Link Got Expired");
            }
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Error while validating user", e);
            return internalServerError();
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createPassword()
    {
        JsonNode json = request().body().asJson();
        UserDto userDto = Json.fromJson(json, UserDto.class);
        if (userDto == null || userDto.getEmail() == null || userDto.getNewPassword() == null || userDto.getNewPassword() == RestUrlAttribute.EMPTY_QUOTES || userDto.getEmail() == RestUrlAttribute.EMPTY_QUOTES)
        {
            CMSLogger.error(logger, "Invalid inputs", null);
            return badRequest();
        }
        try
        {
            Integer idUser = userService.validateNewUserMailId(userDto.getEmail());
            if (idUser == null)
            {
                CMSLogger.error(logger, "Mail id is invalid", null);
                return unauthorized();
            }
            CMSLogger.info(logger, "idUser in createPassword " + idUser);
            boolean passwordsList = userService.verifyPreviousPasswords(idUser, userDto.getNewPassword());
            if (passwordsList)
            {
                CMSLogger.info(logger, "Password is new and not under previous ones");
                UserCredential userCredential = userService.createUserCredentialObject(idUser, userDto.getNewPassword());
                userDto.setIdUser(CryptoBinderUtil.getEncryptId(userCredential.getIdUser().toString()));
                List<Password> password = userService.expireLinks(idUser);
                CMSLogger.info(logger, "password Links List" + password);
            }
            else
            {
                CMSLogger.info(logger, "previous passwords are equal");
                return badRequest("Previous passwords are equal");
            }
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Error while creating password for user", e);
            return internalServerError();
        }
        return ok(Json.toJson(userDto));
    }

    public Result validateForgetUserMailId(String emailId)
    {
        if (emailId != null && !emailId.equals(RestUrlAttribute.EMPTY_QUOTES))
        {
            Integer idUser = userService.validateNewUserMailId(emailId);
            if (idUser == null)
            {
                CMSLogger.error(logger, "Your mail id is not registered. Please contact your administrator. Or User is in active", null);
                return ok(Json.toJson(RestUrlAttribute.EMPTY_QUOTES));
            }
            CMSLogger.info(logger, "emailId" + emailId);
            try
            {
                List<Password> expireLinks = userService.expireLinks(idUser);
                CMSLogger.info(logger, "expireLinks List" + expireLinks);
                String keyString = generateSecretKey();
                String encryptText = CryptoBinderUtil.getEncryptId(emailId);
                Password password = userService.createPasswordEntry(encryptText, emailId, idUser, keyString);
                String baseUrl = RestUrlAttribute.REST_BASE_URL;
                if (baseUrl.endsWith("/"))
                {
                    baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
                }
                CMSLogger.info(logger, "baseUrl.substring(0, baseUrl.length());" + baseUrl);
                String createPasswordUrl = baseUrl;// + com.crackers.controllers.web.routes.PasswordController.forgotPassword(password.getIdPassword(), encryptText, 0).url();
                CMSLogger.info(logger, "createPasswordUrl" + createPasswordUrl);
                URLEncoder.encode(createPasswordUrl, "UTF-8");
                Map<String, String> map = new HashMap<>();
                User userCredential2 = userRepository.findOne(idUser.longValue());
                EmailTemplate emailTemplate = new EmailTemplate();
                if (userCredential2.getIdSource() != null && userCredential2.getIdSource().intValue() == 1)
                {
                    CMSLogger.info(logger, "forgot mail");
                    emailTemplate = emailTemplateRepository.findOne(CommonConstants.FORGET_PASSWORD.longValue());
                    map.put("[[Forgot Password]]", createPasswordUrl);
                }
                map.put(CommonConstants.POWERED_BY_XXX, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.POWERED_BY_XXX)));
                map.put(CommonConstants.SUPPORT_EMAIL_ID, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.SUPPORT_EMAIL_ID)));
                emailTemplate.setBody(EmailHelper.getFilledEmailTemplate(map, emailTemplate.getBody()));
                UserInfo userInfo = new UserInfo();
                BeanUtil.copyBeanProperties(userCredential2, userInfo, new ArrayList<>());
                List<UserInfo> userInfos = new ArrayList<>();
                userInfos.add(userInfo);
                CMSLogger.info(logger, "emailTemplate" + emailTemplate);
                sendEMail.sendMail(emailTemplate, idUser, userRepository.validateById(idUser), userInfos);
                return ok(Json.toJson(RestUrlAttribute.EMPTY_QUOTES));
            }
            catch (Exception e)
            {
                CMSLogger.error(logger, "Error while validating forget password for user", e);
                return internalServerError();
            }
        }
        CMSLogger.error(logger, "Your mail id exist in user table,but not in Email table", null);
        return ok(Json.toJson(RestUrlAttribute.EMPTY_QUOTES));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createForgetPassword()
    {
        JsonNode json = request().body().asJson();
        UserDto userDto = Json.fromJson(json, UserDto.class);
        if (userDto == null || userDto.getEmail() == null || userDto.getNewPassword() == null || userDto.getNewPassword() == RestUrlAttribute.EMPTY_QUOTES || userDto.getEmail() == RestUrlAttribute.EMPTY_QUOTES)
        {
            CMSLogger.error(logger, "Invalid inputs", null);
            return badRequest();
        }
        try
        {
            Integer idUser = userService.validateNewUserMailId(userDto.getEmail());
            if (idUser == null)
            {
                CMSLogger.error(logger, "Mail id is invalid", null);
                return unauthorized();
            }
            String hashedKey = userService.getUserCredential(idUser);
            if (hashedKey != null && !hashedKey.equals(RestUrlAttribute.EMPTY_QUOTES))
            {
                boolean passwordsList = userService.verifyPreviousPasswords(idUser, userDto.getNewPassword());
                CMSLogger.info(logger, "passwordsList" + passwordsList);
                if (passwordsList)
                {
                    CMSLogger.info(logger, "IdUser is Valid " + idUser);
                    UserCredential userCredential = userService.getUserCredentialObject(idUser);
                    String newPassword = encryptPassword(userDto.getNewPassword(), CommonConstants.SALT_KEY, CommonConstants.ENCRYPTION_ALGORITHM);
                    if (userCredential != null && !(hashedKey.equals(newPassword)))
                    {
                        Integer credential = userService.createForgetPassword(idUser, userDto, userCredential.getSaltKey());
                        userDto.setIdUser(CryptoBinderUtil.getEncryptId(credential.toString()));
                        List<Password> password = userService.expireLinks(idUser);
                        CMSLogger.info(logger, "password Links List" + password);
                        CMSLogger.info(logger, "Credentials saved");
                    }
                }
                else
                {
                    CMSLogger.info(logger, "previous passwords are equal");
                    return badRequest("Previous passwords are equal");
                }
            }
            else if (hashedKey == null)
            {
                boolean passwordsList = userService.verifyPreviousPasswords(idUser, userDto.getNewPassword());
                CMSLogger.info(logger, "First time passwords reset" + passwordsList);
                if (passwordsList)
                {
                    CMSLogger.info(logger, "IdUser is Valid " + idUser);
                    Integer credential = userService.createForgetPassword(idUser, userDto, CommonConstants.SALT_KEY);
                    userDto.setIdUser(CryptoBinderUtil.getEncryptId(credential.toString()));
                    List<Password> password = userService.expireLinks(idUser);
                    CMSLogger.info(logger, "password Links List" + password);
                    CMSLogger.info(logger, "Credentials saved");
                }
                else
                {
                    CMSLogger.info(logger, "previous passwords are equal");
                    return badRequest("Previous passwords are equal");
                }
            }
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Error while changing forget password for user", e);
            return internalServerError();
        }
        return ok(Json.toJson(userDto));
    }

    private static String encryptPassword(String data, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);
        byte[] rawSig = mac.doFinal(data.getBytes());
        return new String(Hex.encodeHex(rawSig));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createResetPassword(String uniqueId)
    {
        JsonNode json = request().body().asJson();
        UserDto userDto = Json.fromJson(json, UserDto.class);
        if (uniqueId == null || userDto.getNewPassword() == null || userDto.getOldPassword() == null || userDto.getNewPassword() == RestUrlAttribute.EMPTY_QUOTES || userDto.getOldPassword() == RestUrlAttribute.EMPTY_QUOTES)
        {
            CMSLogger.error(logger, "Invalid inputs", null);
            return badRequest("Invalid inputs");
        }
        try
        {
            Integer idUser = CacheManager.getIdUserFromCache(uniqueId);
            CMSLogger.info(logger, "Current User Id" + idUser);
            UserCredential userCredential = userService.getUserCredentialObject(idUser);
            boolean passwordsList = userService.verifyPreviousPasswords(idUser, userDto.getNewPassword());
            if (passwordsList)
            {
                if (userCredential != null)
                {
                    String oldPassword = encryptPassword(userDto.getOldPassword(), CommonConstants.SALT_KEY, CommonConstants.ENCRYPTION_ALGORITHM);
                    CMSLogger.info(logger, "userCredential.getHashedKey()" + userCredential.getHashedKey() + "oldPassword " + oldPassword);
                    if (userCredential.getHashedKey().equals(oldPassword))
                    {
                        CMSLogger.info(logger, "Going to Update Password");
                        Integer credential = userService.createForgetPassword(idUser, userDto, userCredential.getSaltKey());
                        CMSLogger.info(logger, "After Updating Password");
                        userDto.setIdUser(CryptoBinderUtil.getEncryptId(credential.toString()));
                    }
                    else
                    {
                        CMSLogger.info(logger, "Returning BadRequest");
                        return badRequest("Password entered does not match your old password!");
                    }
                }
            }
            else
            {
                return badRequest("Previous passwords are equal");
            }
        }
        catch (Exception e)
        {
            CMSLogger.error(logger, "Error while reseting password for user", e);
            return internalServerError();
        }
        return ok(Json.toJson(userDto));
    }
}