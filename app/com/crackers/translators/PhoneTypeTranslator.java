package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.dto.PhoneTypeDto;
import com.crackers.model.PhoneType;

@Component
public class PhoneTypeTranslator
{

    private static Logger logger = Logger.getLogger(PhoneTypeTranslator.class);

    public PhoneTypeDto translateToPhoneTypeDto(PhoneType phoneType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if (phoneType == null)
        {
            return null;
        }
        PhoneTypeDto phoneTypeDto = new PhoneTypeDto();
        CMSLogger.info(logger, "PhoneTypeDto:" + phoneTypeDto);
        BeanUtil.copyBeanProperties(phoneType, phoneTypeDto, null);
        return phoneTypeDto;
    }

    public Integer getPhoneTypeId(PhoneTypeDto statusDto)
    {
        if (statusDto == null)
        {
            return null;
        }
        return statusDto.getIdPhoneType();
    }

    public PhoneTypeDto translateToPhoneTypeDto(Integer idPhoneType) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        if (idPhoneType == null)
        {
            return null;
        }
        PhoneTypeDto statusDto = new PhoneTypeDto();
        statusDto.setIdPhoneType(idPhoneType);
        return statusDto;
    }

    public PhoneType translateDtoToPhoneType(PhoneTypeDto phoneTypeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if (phoneTypeDto == null)
        {
            return null;
        }
        PhoneType phoneType = new PhoneType();
        CMSLogger.info(logger, "PhoneTypeDto:" + phoneTypeDto);
        BeanUtil.copyBeanProperties(phoneTypeDto, phoneType, null);
        return phoneType;
    }

    public List<PhoneTypeDto> translateListToPhoneTypeDto(List<PhoneType> phoneType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if (phoneType == null)
        {
            return null;
        }
        List<PhoneTypeDto> phoneTypeDtos = new ArrayList<>();
        Iterator<PhoneType> phoneTypeIterator = phoneType.iterator();
        while (phoneTypeIterator.hasNext())
        {
            PhoneType phoneType1 = phoneTypeIterator.next();
            PhoneTypeDto phoneTypeDto = new PhoneTypeDto();
            CMSLogger.info(logger, "PhoneTypeDto:" + phoneTypeDto);
            BeanUtil.copyBeanProperties(phoneType1, phoneTypeDto, null);
            phoneTypeDtos.add(phoneTypeDto);
        }
        return phoneTypeDtos;
    }
}
