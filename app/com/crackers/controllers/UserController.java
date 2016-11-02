/**
 * @author rajagja
 * @date Nov 1, 2016
 */
package com.crackers.controllers;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.relation.Relation;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Result;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.common.EmailHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.EmailDto;
import com.crackers.dto.UserDto;
import com.crackers.dto.UserInfo;
import com.crackers.model.Password;
import com.crackers.model.UserCredential;
import com.crackers.services.UserService;
import com.crackers.util.CacheManager;
import com.crackers.util.CryptoBinderUtil;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class UserController extends BaseController
{

	private static Logger	logger	= Logger.getLogger(UserController.class);
	@Resource
	private UserService		userService;

	public Result createUser(String uniqueId)
	{
		Integer idUser;
		CMSLogger.info(logger, "User Controller Method Call:" + "createUser( )");
		JsonNode json = request().body().asJson();
		UserDto userDto = Json.fromJson(json, UserDto.class);
		if (uniqueId == null || userDto == null || (userDto.getFirstName() == null && userDto.getLastName() == null)
				|| (userDto.getFirstName().equals(RestUrlAttribute.EMPTY_QUOTES) && userDto.getLastName().equals(RestUrlAttribute.EMPTY_QUOTES)))
		{
			CMSLogger.error(logger, "Invalid inputs", null);
			return badRequest();
		}
		idUser = CacheManager.getIdUserFromCache(uniqueId);
		if (idUser == null)
		{
			CMSLogger.error(logger, "Invalid UniqueId", null);
			return unauthorized();
		}
		if (userService.getUser(userDto.getUserName()) != 0)
		{
			CMSLogger.error(logger, "User name allready exist.", null);
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
			if ((userDto.getLastName() != RestUrlAttribute.EMPTY_QUOTES && userDto.getLastName() != null) || (userDto.getFirstName() != RestUrlAttribute.EMPTY_QUOTES && userDto.getFirstName() != null))
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
							Password password = userService.createPasswordEntry(encryptedText, emailDto.getEmail(), userId, keyString, NotificationMasterConstants.getAccessConstants(NotificationMasterConstants.NEW_PASSWORD));
							String baseUrl = RestUrlAttribute.REST_BASE_URL;
							if (baseUrl.endsWith("/"))
							{
								baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
							}
							CMSLogger.info(logger, "baseUrl.substring(0, baseUrl.length());" + baseUrl);
							String createPasswordUrl = baseUrl + com.vs.cms.controllers.web.routes.PasswordController.createPassword(password.getIdPassword(), password.getEncryptText()).url();
							CMSLogger.info(logger, "createPasswordUrl" + createPasswordUrl);
							URLEncoder.encode(createPasswordUrl, "UTF-8");
							UserCredential userCredential = userService.getUserCredentialObject(userId);
							if (userCredential == null)
							{
								Map<String, String> map = new HashMap<>();
								EmailTemplate emailTemplate = new EmailTemplate();
								if (userDtoCreated.getUserSourceDto() != null && userDtoCreated.getUserSourceDto().getIdSource().intValue() == 1)
								{
									emailTemplate = emailTemplateRepository.findOne(NotificationMasterConstants.getAccessConstants(NotificationMasterConstants.NEW_PASSWORD));
									map.put("[[New Password]]", createPasswordUrl);
								}
								if (userDtoCreated.getUserSourceDto() != null && userDtoCreated.getUserSourceDto().getIdSource().intValue() > 1)
								{
									emailTemplate = emailTemplateRepository.findOne(NotificationMasterConstants.getAccessConstants(NotificationMasterConstants.AD_USER));
									map.put("[[New Password]]", baseUrl);
								}
								map.put(ApplicationContext.POWERED_BY_VERNALIS, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.getValues(CommonConstants.POWERED_BY_VERNALIS))));
								map.put(ApplicationContext.SUPPORT_EMAIL_ID, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.getValues(CommonConstants.SUPPORT_EMAIL_ID))));
								emailTemplate.setBody(EmailHelper.getFilledEmailTemplate(map, emailTemplate.getBody()));
								UserInfo userInfo = new UserInfo();
								BeanUtil.copyBeanProperties(userDtoCreated, userInfo, new ArrayList<>());
								userInfo.setIdUser(CryptoBinderUtil.getDecryptId(userDtoCreated.getIdUser()));
								List<UserInfo> userInfos = new ArrayList<>();
								userInfos.add(userInfo);
								sendEMail.sendMail(emailTemplate, userId, Relation.getCode(Relation.USER_SETTING), userRepository.validateById(idUser), userInfos);
							}
						}
					}
				}
			}
			userSettingsElasticSearchIndexObserver.update(userDtoCreated);
			final UserDto userDtoUpdatedObj = userDtoCreated;
			Promise.promise(new Function0<Object>() {

				@Override
				public Object apply() throws Throwable
				{
					try
					{
						CMSLogger.info(logger, "Game report indexing Started");
						userSettingsElasticSearchIndexObserver.setGameReportAccess(userDtoUpdatedObj);
						CMSLogger.info(logger, "Game report indexing done");
						CMSLogger.info(logger, "Incident indexing Started");
						userSettingsElasticSearchIndexObserver.setIncidentAccess(userDtoUpdatedObj);
						CMSLogger.info(logger, "incident indexing done");
					}
					catch (Exception e)
					{
						CMSLogger.error(logger, "Exception while updating the Incident and GameReports in Create Process", e);
					}
					return null;
				}
			});
			return ok(Json.toJson(userDtoCreated));
		}
		catch (Exception e)
		{
			CMSLogger.error(logger, "Exception while user Creation", e);
			return internalServerError();
		}
	}
}