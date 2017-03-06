package com.crackers.controllers.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CommonConstants;
import com.crackers.common.CrackersLogger;
import com.crackers.common.RestCallService;
import com.crackers.common.RestHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.controllers.BaseController;
import com.crackers.dto.ContactDetailsDto;
import com.crackers.dto.EmailDto;
import com.crackers.dto.PhoneNumberDto;
import com.crackers.dto.PhoneTypeDto;
import com.crackers.dto.UserDto;
import com.crackers.vo.ContactDetailsVO;
import com.crackers.vo.EmailVO;
import com.crackers.vo.PhoneVO;
import com.crackers.vo.UserDetailsVO;
import com.crackers.vo.WSError;
import com.fasterxml.jackson.databind.JsonNode;

import play.Play;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;
import play.mvc.Result;

@Component
public class UserSettingWebController extends BaseController {

	private static Logger logger = Logger.getLogger(UserSettingWebController.class);

	public Result createUser() {
		try {
			JsonNode json = request().body().asJson();
			CrackersLogger.info(logger, "Inside postUserService of UserSettingWebController");
			String baseUrl = RestUrlAttribute.REST_BASE_URL;
			String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.NEW_USER_POST));
			CrackersLogger.info(logger, "serviceUrl createNewUser" + serviceUrl);
			if (json.isObject()) {
				UserDetailsVO userDetailsVO = Json.fromJson(json, UserDetailsVO.class);
				UserDto userDto = new UserDto();
				BeanUtil.copyBeanProperties(userDetailsVO, userDto, new ArrayList<>());
				if (userDetailsVO.getPhoneNumberVos() != null) {
					List<PhoneNumberDto> phoneNumberFinal = new ArrayList<>();
					Iterator<PhoneVO> phoneNumberVoFinalIterator = userDetailsVO.getPhoneNumberVos().iterator();
					while (phoneNumberVoFinalIterator.hasNext()) {
						PhoneVO phoneVoNode = phoneNumberVoFinalIterator.next();
						PhoneNumberDto dto = new PhoneNumberDto();
						if (phoneVoNode != null) {
							BeanUtil.copyBeanProperties(phoneVoNode, dto, new ArrayList<>());
							PhoneTypeDto phoneTypeDto = new PhoneTypeDto();
							phoneTypeDto.setId(phoneVoNode.getIdPhoneType());
							dto.setPhoneTypeDto(phoneTypeDto);
						}
						phoneNumberFinal.add(dto);
					}
					userDto.setPhoneNumberDtos(phoneNumberFinal);
				}
				if (userDetailsVO.getEmailVos() != null) {
					List<EmailDto> emailFinal = new ArrayList<>();
					Iterator<EmailVO> emailVoFinalIterator = userDetailsVO.getEmailVos().iterator();
					while (emailVoFinalIterator.hasNext()) {
						EmailVO emailVoNode = emailVoFinalIterator.next();
						EmailDto dto = new EmailDto();
						if (emailVoNode != null) {
							BeanUtil.copyBeanProperties(emailVoNode, dto, new ArrayList<>());
						}
						emailFinal.add(dto);
					}
					userDto.setEmailDtos(emailFinal);
				}
				if (userDetailsVO.getEmailVos() != null) {
					userDto.setUserName(userDetailsVO.getEmailVos().get(0).getEmail());
				}
				if (userDetailsVO.getContactDetailsVos() != null) {
					List<ContactDetailsDto> contactDetailFinal = new ArrayList<>();
					Iterator<ContactDetailsVO> contactDetailFinalIterator = userDetailsVO.getContactDetailsVos().iterator();
					while (contactDetailFinalIterator.hasNext()) {
						ContactDetailsVO contactDetailVoNode = contactDetailFinalIterator.next();
						ContactDetailsDto dto = new ContactDetailsDto();
						if (contactDetailVoNode != null) {
							BeanUtil.copyBeanProperties(contactDetailVoNode, dto, new ArrayList<>());
						}
						contactDetailFinal.add(dto);
					}
					userDto.setContactDetailsDtos(contactDetailFinal);
				}
				Object object = userDto;
				WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
				requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
				CrackersLogger.info(logger, "requestHolder in UserSettingWebController" + requestHolder);
				JsonNode userCreatedNode = RestCallService.callPostRestService(requestHolder, object);
				if (userCreatedNode.isObject()) {
					Object objectWsObject = Json.fromJson(userCreatedNode, Object.class);
					if (objectWsObject instanceof WSError) {
						CrackersLogger.info(logger, "Errorr message");
						WSError wsError = (WSError) objectWsObject;
						if (wsError.getIdWSError().equals(RestCallService.BAD_REQUEST)) {
							return badRequest(wsError.getWsError());
						}
						else if (wsError.getIdWSError().equals(RestCallService.UNAUTHORIZED)) {
							return unauthorized();
						}
						else if (wsError.getIdWSError().equals(RestCallService.NO_CONTENT)) {
							return noContent();
						}
					}
					else {
						UserDto userDtoCreated = (UserDto) objectWsObject;
						return invokeLogin(userDtoCreated.getUserName(), userDtoCreated.getNewPassword());
					}
				}
			}
		}
		catch (Exception exception) {
			CrackersLogger.error(logger, "Error while creating new User", exception);
		}
		return internalServerError();
	}
	
	public Result updateUser(Long idUser)
    {
        try
        {
            JsonNode json = request().body().asJson();
            Object object;
            CrackersLogger.info(logger, "Inside putUserService of UserSettingWebController");
            String baseUrl = RestUrlAttribute.REST_BASE_URL;
            String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.USER_PUT));
            CrackersLogger.info(logger, "serviceUrl updateUser" + serviceUrl);
            if (json.isObject())
            {
                UserDetailsVO userDetailsVO = Json.fromJson(json, UserDetailsVO.class);
                // Setting values from Vo to Dto before service Call
                UserDto userDto = new UserDto();
                BeanUtil.copyBeanProperties(userDetailsVO, userDto, new ArrayList<>());
                if (userDetailsVO.getPhoneNumberVos() != null)
                {
                    List<PhoneNumberDto> phoneNumberFinal = new ArrayList<>();
                    Iterator<PhoneVO> phoneNumberVoFinalIterator = userDetailsVO.getPhoneNumberVos().iterator();
                    while (phoneNumberVoFinalIterator.hasNext())
                    {
                        PhoneVO phoneVoNode = phoneNumberVoFinalIterator.next();
                        PhoneNumberDto dto = new PhoneNumberDto();
                        if (phoneVoNode != null && phoneVoNode.getPhoneNumber() != null)
                        {
                            CrackersLogger.info(logger, "PhoneNumber in WebController of UserSettingsWebController" + phoneVoNode.getPhoneNumber());
                            BeanUtil.copyBeanProperties(phoneVoNode, dto, new ArrayList<>());
                            PhoneTypeDto phoneTypeDto = new PhoneTypeDto();
                            phoneTypeDto.setId(phoneVoNode.getIdPhoneType());
                            dto.setPhoneTypeDto(phoneTypeDto);
                        }
                        phoneNumberFinal.add(dto);
                    }
                    userDto.setPhoneNumberDtos(phoneNumberFinal);
                }
                if (userDetailsVO.getEmailVos() != null)
                {
                    List<EmailDto> emailFinal = new ArrayList<>();
                    Iterator<EmailVO> emailVoFinalIterator = userDetailsVO.getEmailVos().iterator();
                    while (emailVoFinalIterator.hasNext())
                    {
                        EmailVO emailVoNode = emailVoFinalIterator.next();
                        EmailDto dto = new EmailDto();
                        if (emailVoNode != null && emailVoNode.getEmail() != null)
                        {
                            CrackersLogger.info(logger, "Email in WebController of UserSettingsWebController" + emailVoNode.getEmail());
                            BeanUtil.copyBeanProperties(emailVoNode, dto, new ArrayList<>());
                        }
                        emailFinal.add(dto);
                    }
                    userDto.setEmailDtos(emailFinal);
                }
                if (userDetailsVO.getEmailVos() != null)
                {
                    userDto.setUserName(userDetailsVO.getEmailVos().get(0).getEmail());
                }
                if (userDetailsVO.getContactDetailsVos() != null)
                {
                    List<ContactDetailsDto> contactDetailFinal = new ArrayList<>();
                    Iterator<ContactDetailsVO> contactDetailFinalIterator = userDetailsVO.getContactDetailsVos().iterator();
                    while (contactDetailFinalIterator.hasNext())
                    {
                        ContactDetailsVO contactDetailVoNode = contactDetailFinalIterator.next();
                        ContactDetailsDto dto = new ContactDetailsDto();
                        if (contactDetailVoNode != null)
                        {
                            CrackersLogger.info(logger, "Street in WebController of UserSettingsWebController" + contactDetailVoNode.getStreet());
                            BeanUtil.copyBeanProperties(contactDetailVoNode, dto, new ArrayList<>());
                        }
                        contactDetailFinal.add(dto);
                    }
                    userDto.setContactDetailsDtos(contactDetailFinal);
                }
                object = userDto;
                CrackersLogger.info(logger, "Final Dto to sent for the save:" + userDto);
                WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
                requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
                requestHolder.setQueryParameter(CommonConstants.ID_USER, idUser.toString());
                CrackersLogger.info(logger, "requestHolder in UserSettingWebController" + requestHolder);
                JsonNode userCreatedNode = RestCallService.callPutRestService(requestHolder, object);
                if (userCreatedNode.isObject())
                {
                    Object objectWsObject = Json.fromJson(userCreatedNode, Object.class);
                    if (objectWsObject instanceof WSError)
                    {
                        CrackersLogger.info(logger, "Errorr message");
                        WSError wsError = (WSError) objectWsObject;
                        if (wsError.getIdWSError().equals(400))
                        {
                            return badRequest(wsError.getWsError());
                        }
                        else if (wsError.getIdWSError().equals(RestCallService.UNAUTHORIZED))
                        {
                            return unauthorized();
                        }
                        else if (wsError.getIdWSError().equals(RestCallService.NO_CONTENT))
                        {
                            return noContent();
                        }
                    }
                    else
                    {
                        return ok(Json.toJson(objectWsObject));
                    }
                }
            }
        }
        catch (Exception exception)
        {
            CrackersLogger.error(logger, "Error while updating User", exception);
        }
        return internalServerError();
    }
}