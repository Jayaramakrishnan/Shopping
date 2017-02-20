package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CrackersLogger;
import com.crackers.dto.PhoneNumberDto;
import com.crackers.model.PhoneNumber;

@Component
public class PhoneNumberTranslator
{

    private Logger logger = Logger.getLogger(PhoneNumberTranslator.class);

    public List<PhoneNumberDto> translateListToPhoneNumberDto(List<PhoneNumber> phoneNumbers) throws InvocationTargetException
    {
        CrackersLogger.info(logger, "Inside PhoneNumberTranslator : getPhoneNumber()");
        List<PhoneNumberDto> phoneNumberDtos = new ArrayList<>();
        if (phoneNumbers == null)
        {
            return phoneNumberDtos;
        }
        Iterator<PhoneNumber> phoneNumberIterator = phoneNumbers.iterator();
        while (phoneNumberIterator.hasNext())
        {
            PhoneNumber phoneNumber1 = phoneNumberIterator.next();
            PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
            PhoneTypeTranslator phoneTypeTranslator = new PhoneTypeTranslator();
            BeanUtil.copyBeanProperties(phoneNumber1, phoneNumberDto, new ArrayList<>());
            phoneNumberDto.setPhoneTypeDto(phoneTypeTranslator.translateToPhoneTypeDto(phoneNumber1.getIdPhoneType()));
            phoneNumberDtos.add(phoneNumberDto);
        }
        return phoneNumberDtos;
    }

    public PhoneNumberDto translateToPhoneNumberDto(PhoneNumber phoneNumber) throws InvocationTargetException
    {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        if (phoneNumber == null)
        {
            return phoneNumberDto;
        }
        CrackersLogger.info(logger, "PhoneNumberDto:" + phoneNumberDto);
        PhoneTypeTranslator phoneTypeTranslator = new PhoneTypeTranslator();
        BeanUtil.copyBeanProperties(phoneNumber, phoneNumberDto, new ArrayList<>());
        phoneNumberDto.setPhoneTypeDto(phoneTypeTranslator.translateToPhoneTypeDto(phoneNumber.getIdPhoneType()));
        return phoneNumberDto;
    }

    public PhoneNumber translateDtoToPhoneNumber(PhoneNumberDto phoneNumberDto) throws InvocationTargetException
    {
        PhoneNumber phoneNumber = new PhoneNumber();
        if (phoneNumberDto == null)
        {
            return phoneNumber;
        }
        CrackersLogger.info(logger, "PhoneNumberDto:" + phoneNumberDto);
        PhoneTypeTranslator phoneTypeTranslator = new PhoneTypeTranslator();
        BeanUtil.copyBeanProperties(phoneNumberDto, phoneNumber, new ArrayList<>());
        phoneNumber.setIdPhoneType(phoneTypeTranslator.getPhoneTypeId(phoneNumberDto.getPhoneTypeDto()));
        return phoneNumber;
    }

    public Integer getPhoneNumberId(PhoneNumberDto phoneNumberDto)
    {
        if (phoneNumberDto == null)
        {
            return null;
        }
        return phoneNumberDto.getIdPhoneNumber();
    }

    public PhoneNumberDto translateToPhoneNumberDto(Integer idPhoneNumber) throws InvocationTargetException
    {
        if (idPhoneNumber == null)
        {
            return null;
        }
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setIdPhoneNumber(idPhoneNumber);
        return phoneNumberDto;
    }
}