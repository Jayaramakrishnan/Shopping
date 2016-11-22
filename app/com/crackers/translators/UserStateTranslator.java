package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.dto.UserStateDto;
import com.crackers.model.UserState;

@Component
public class UserStateTranslator
{

    public List<UserStateDto> translateListToUserStateDto(List<UserState> userStates) throws Exception
    {
        List<UserStateDto> userStateDtos = new ArrayList<>();
        if (userStates == null)
        {
            return userStateDtos;
        }
        Iterator<UserState> userStateIterator = userStates.iterator();
        while (userStateIterator.hasNext())
        {
            UserState userState1 = userStateIterator.next();
            UserStateDto userStateDto = new UserStateDto();
            BeanUtil.copyBeanProperties(userState1, userStateDto, new ArrayList<>());
            userStateDtos.add(userStateDto);
        }
        return userStateDtos;
    }

    public UserStateDto translateToUserStateDto(UserState userState) throws InvocationTargetException
    {
        UserStateDto userStateDto = new UserStateDto();
        if (userState == null)
        {
            return userStateDto;
        }
        BeanUtil.copyBeanProperties(userState, userStateDto, new ArrayList<>());
        return userStateDto;
    }

    public UserState translateDtoToUserState(UserStateDto userStateDto) throws InvocationTargetException
    {
        UserState userState = new UserState();
        if (userStateDto == null)
        {
            return userState;
        }
        BeanUtil.copyBeanProperties(userStateDto, userState, new ArrayList<>());
        return userState;
    }

    public Integer getUserStateId(UserStateDto userStateDto)
    {
        if (userStateDto == null)
        {
            return null;
        }
        return userStateDto.getIdUserState();
    }

    public UserStateDto translateToUserStateDto(Integer idUserState)
    {
        if (idUserState == null)
        {
            return null;
        }
        UserStateDto userStateDto = new UserStateDto();
        userStateDto.setIdUserState(idUserState);
        return userStateDto;
    }
}