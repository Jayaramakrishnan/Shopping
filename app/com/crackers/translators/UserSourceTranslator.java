package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.dto.UserSourceDto;
import com.crackers.model.UserSource;

@Component
public class UserSourceTranslator
{

    public List<UserSourceDto> translateListToUserSourceDto(List<UserSource> userSources) throws InvocationTargetException
    {
        List<UserSourceDto> userSourceDtos = new ArrayList<>();
        if (userSources == null)
        {
            return userSourceDtos;
        }
        Iterator<UserSource> userSourceIterator = userSources.iterator();
        while (userSourceIterator.hasNext())
        {
            UserSource userSource1 = userSourceIterator.next();
            UserSourceDto userSourceDto = new UserSourceDto();
            BeanUtil.copyBeanProperties(userSource1, userSourceDto, new ArrayList<>());
            userSourceDtos.add(userSourceDto);
        }
        return userSourceDtos;
    }

    public UserSourceDto translateToUserSourceDto(UserSource userSource) throws InvocationTargetException
    {
        UserSourceDto userSourceDto = new UserSourceDto();
        if (userSource == null)
        {
            return userSourceDto;
        }
        BeanUtil.copyBeanProperties(userSource, userSourceDto, new ArrayList<>());
        return userSourceDto;
    }

    public UserSource translateDtoToUserSource(UserSourceDto userSourceDto) throws InvocationTargetException
    {
        UserSource userSource = new UserSource();
        if (userSourceDto == null)
        {
            return userSource;
        }
        BeanUtil.copyBeanProperties(userSourceDto, userSource, new ArrayList<>());
        return userSource;
    }

    public Integer getUserSourceId(UserSourceDto userSourceDto)
    {
        if (userSourceDto == null)
        {
            return null;
        }
        return userSourceDto.getIdSource();
    }

    public UserSourceDto translateToUserSourceDto(Integer idUserSource) throws InvocationTargetException
    {
        if (idUserSource == null)
        {
            return null;
        }
        UserSourceDto userSourceDto = new UserSourceDto();
        userSourceDto.setIdSource(idUserSource);
        return userSourceDto;
    }
}