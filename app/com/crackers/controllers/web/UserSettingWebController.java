package com.crackers.controllers.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import play.Play;
import play.mvc.Result;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.common.CommonConstants;
import com.crackers.common.RestCallService;
import com.crackers.common.RestHelper;
import com.crackers.common.RestUrlAttribute;
import com.crackers.controllers.BaseController;
import com.crackers.dto.ContactDetailsDto;
import com.crackers.dto.EmailDto;
import com.crackers.dto.PhoneNumberDto;
import com.crackers.dto.PhoneTypeDto;
import com.crackers.dto.RoleDto;
import com.crackers.dto.UserDto;
import com.crackers.dto.UserSourceDto;
import com.crackers.dto.UserStateDto;
import com.crackers.vo.ContactDetailsVO;
import com.crackers.vo.EmailVO;
import com.crackers.vo.PhoneVO;
import com.crackers.vo.UserDetailsVO;
import com.crackers.vo.WSError;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class UserSettingWebController extends BaseController
{

    private static Logger logger = Logger.getLogger(UserSettingWebController.class);

    public Result createUser()
    {
        try
        {
            JsonNode json = request().body().asJson();
            CMSLogger.info(logger, "Inside postUserService of UserSettingWebController");
            String baseUrl = RestUrlAttribute.REST_BASE_URL;
            String serviceUrl = baseUrl.concat(Play.application().configuration().getString(RestUrlAttribute.NEW_USER_POST));
            CMSLogger.info(logger, "serviceUrl createNewUser" + serviceUrl);
            if (json.isObject())
            {
                UserDetailsVO userDetailsVO = Json.fromJson(json, UserDetailsVO.class);
                UserDto userDto = new UserDto();
                BeanUtil.copyBeanProperties(userDetailsVO, userDto, new ArrayList<>());
                if (userDetailsVO.getIdRole() != null)
                {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setIdRole(userDetailsVO.getIdRole());
                    roleDto.setRole(userDetailsVO.getRole());
                    userDto.setRoleDto(roleDto);
                }
                if (userDetailsVO.getIdSource() != null)
                {
                    UserSourceDto userSourceDto = new UserSourceDto();
                    userSourceDto.setIdSource(userDetailsVO.getIdSource());
                    userSourceDto.setSource(userDetailsVO.getSource());
                    userDto.setUserSourceDto(userSourceDto);
                }
                if (userDetailsVO.getIdUserState() != null)
                {
                    UserStateDto userStateDto = new UserStateDto();
                    userStateDto.setIdUserState(userDetailsVO.getIdUserState());
                    userStateDto.setState(userDetailsVO.getState());
                    userDto.setUserStateDto(userStateDto);
                }
                if (userDetailsVO.getPhoneNumberVos() != null)
                {
                    List<PhoneNumberDto> phoneNumberFinal = new ArrayList<>();
                    Iterator<PhoneVO> phoneNumberVoFinalIterator = userDetailsVO.getPhoneNumberVos().iterator();
                    while (phoneNumberVoFinalIterator.hasNext())
                    {
                        PhoneVO phoneVoNode = phoneNumberVoFinalIterator.next();
                        PhoneNumberDto dto = new PhoneNumberDto();
                        if (phoneVoNode != null)
                        {
                            BeanUtil.copyBeanProperties(phoneVoNode, dto, new ArrayList<>());
                            PhoneTypeDto phoneTypeDto = new PhoneTypeDto();
                            phoneTypeDto.setIdPhoneType(phoneVoNode.getIdPhoneType());
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
                        if (emailVoNode != null)
                        {
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
                            BeanUtil.copyBeanProperties(contactDetailVoNode, dto, new ArrayList<>());
                        }
                        contactDetailFinal.add(dto);
                    }
                    userDto.setContactDetailsDtos(contactDetailFinal);
                }
                Object object = userDto;
                WSRequestHolder requestHolder = RestHelper.checkProxyAndSetHeader(serviceUrl);
                requestHolder.setQueryParameter(CommonConstants.UNIQUE_ID, session().get(CommonConstants.UNIQUE_ID));
                CMSLogger.info(logger, "requestHolder in UserSettingWebController" + requestHolder);
                JsonNode userCreatedNode = RestCallService.callPostRestService(requestHolder, object);
                if (userCreatedNode.isObject())
                {
                    Object objectWsObject = Json.fromJson(userCreatedNode, Object.class);
                    if (objectWsObject instanceof WSError)
                    {
                        CMSLogger.info(logger, "Errorr message");
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
            CMSLogger.error(logger, "Error while creating new User", exception);
        }
        return internalServerError();
    }
}