package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.dto.UserDto;
import com.crackers.model.User;
import com.crackers.util.CryptoBinderUtil;

@Component
public class PersonTranslator
{

    private static Logger        logger = Logger.getLogger(PersonTranslator.class);
    @Resource
    private UserSourceTranslator userSourceTranslator;
    @Resource
    private UserStateTranslator  userStateTranslator;

    public User translateToPerson(UserDto userDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        User user = new User();
        if (userDto == null)
        {
            return user;
        }
        CMSLogger.info(logger, "Iduser in Person Translator before decrypting: " + userDto.getIdUser());
        List<String> properties = new ArrayList<>();
        BeanUtil.copyBeanProperties(userDto, user, properties);
        if (userDto.getIdUser() != null)
        {
            user.setIdUser(CryptoBinderUtil.getDecryptId(userDto.getIdUser()));
        }
        if (userDto.getUserSourceDto() != null && userDto.getUserSourceDto().getIdSource() != null)
        {
            user.setIdSource(userDto.getUserSourceDto().getIdSource());
        }
        if (userDto.getUserStateDto() != null && userDto.getUserStateDto().getIdUserState() != null)
        {
            user.setIdUserState(userDto.getUserStateDto().getIdUserState());
        }
        return user;
    }

    public UserDto translateUserToDto(User user) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if (user == null)
        {
            return null;
        }
        UserDto userDto = new UserDto();
        BeanUtil.copyBeanProperties(user, userDto, new ArrayList<>());
        if (user.getIdUser() != null)
        {
            userDto.setIdUser(CryptoBinderUtil.getEncryptId(user.getIdUser().toString()));
        }
        userDto.setUserSourceDto(userSourceTranslator.translateToUserSourceDto(user.getIdSource()));
        userDto.setUserStateDto(userStateTranslator.translateToUserStateDto(user.getIdUserState()));
        return userDto;
    }
}