package com.crackers.es.observers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.dto.ContactDetailsDto;
import com.crackers.dto.EmailDto;
import com.crackers.dto.ImageDto;
import com.crackers.dto.PhoneNumberDto;
import com.crackers.dto.UserDto;
import com.crackers.es.ESConstants;
import com.crackers.es.doc.UserDoc;
import com.crackers.exceptions.RegistrationException;
import com.crackers.manager.es.IndexAllRecordsManager;
import com.crackers.model.UserSource;
import com.crackers.repositories.ImageColorCodeRepository;
import com.crackers.repositories.UserRoleRepository;
import com.crackers.repositories.UserSourceRepository;
import com.crackers.repositories.UserStateRepository;
import com.crackers.services.ImageService;
import com.crackers.services.UserService;
import com.crackers.translators.RoleTranslator;
import com.crackers.translators.UserSourceTranslator;
import com.crackers.translators.UserStateTranslator;
import com.crackers.util.CryptoBinderUtil;

/**
 * Created by rajagja on 09/10/15.
 */
@Component
public class UserSettingsElasticSearchIndexObserver
{

    private static Logger            logger = Logger.getLogger(UserSettingsElasticSearchIndexObserver.class);
    @Resource
    private IndexAllRecordsManager   indexAllRecordsManager;
    @Resource
    private UserService              userService;
    @Resource
    private RoleTranslator           roleTranslator;
    @Resource
    private UserRoleRepository       userRoleRepository;
    @Resource
    private ImageColorCodeRepository imageColorCodeRepository;
    @Resource
    private ImageService             imageService;
    @Resource
    private UserSourceRepository     userSourceRepository;
    @Resource
    private UserStateRepository      userStateRepository;
    @Resource
    private UserSourceTranslator     userSourceTranslator;
    @Resource
    private UserStateTranslator      userStateTranslator;

    public void update(UserDto userDto) throws IllegalAccessException, InvocationTargetException, RegistrationException
    {
        List<PhoneNumberDto> phoneNumberDtos = null;
        List<EmailDto> emailDtos = null;
        List<ContactDetailsDto> contactDetailsDtos = null;
        ImageDto imageDto = null;
        if (userDto == null)
        {
            return;
        }
        Integer idUser = CryptoBinderUtil.getDecryptId(userDto.getIdUser());
        phoneNumberDtos = userService.getPhoneNumberList(idUser);
        emailDtos = userService.getEmailList(idUser);
        contactDetailsDtos = userService.getContactDetailsList(idUser);
        imageDto = imageService.getImageDto(idUser);
        if (phoneNumberDtos != null)
        {
            userDto.setPhoneNumberDtos(phoneNumberDtos);
        }
        if (emailDtos != null)
        {
            userDto.setEmailDtos(emailDtos);
        }
        if (contactDetailsDtos != null)
        {
            userDto.setContactDetailsDtos(contactDetailsDtos);
        }
        if (imageDto != null)
        {
            userDto.setImageDto(imageDto);
        }
        if (userDto.getUserSourceDto() != null && userDto.getUserSourceDto().getIdSource() != null)
        {
            UserSource category = userSourceRepository.findOne(userDto.getUserSourceDto().getIdSource().longValue());
            userDto.setUserSourceDto(userSourceTranslator.translateToUserSourceDto(category));
        }
        if (userDto.getUserStateDto() != null && userDto.getUserStateDto().getIdUserState() != null)
        {
            com.crackers.model.UserState category = userStateRepository.findOne(userDto.getUserStateDto().getIdUserState().longValue());
            userDto.setUserStateDto(userStateTranslator.translateToUserStateDto(category));
        }
        if (userDto.getIdImageColorCode() != null)
        {
            userDto.setImageColorCode(imageColorCodeRepository.getImageColorCode(userDto.getIdImageColorCode()));
        }
        userDto.setRoleDto(roleTranslator.translateToRoleDto(userRoleRepository.getRoleByIdUser(idUser)));
        try
        {
            UserDoc userDoc = new UserDoc.UserDocBuilder().setUserDto(userDto).build();
            String userSettingsId = ESConstants.USER_SETTINGS + userDto.getIdUser();
            // indexAllRecordsManager.createUserSettingsIndexById(ESConstants.client, userDoc, userSettingsId, ESConstants.index, ESConstants.userType);
        }
        catch (IllegalArgumentException e)
        {
            CMSLogger.error(logger, "Error while indexing User Details", e);
        }
    }
}