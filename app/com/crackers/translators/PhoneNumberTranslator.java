package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.dto.PhoneNumberDto;
import com.crackers.model.PhoneNumber;

@Component
public class PhoneNumberTranslator
{

    private Logger logger = Logger.getLogger(PhoneNumberTranslator.class);

    public List<PhoneNumberDto> translateListToPhoneNumberDto(List<PhoneNumber> phoneNumbers) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        CMSLogger.info(logger, "Inside PhoneNumberTranslator : getPhoneNumber()");
        List<PhoneNumberDto> phoneNumberDtos = new ArrayList<>();
        if (phoneNumbers == null)
        {
            return phoneNumberDtos;
        }
        Iterator<PhoneNumber> phoneNumberIterator = phoneNumbers.iterator();
        PersonTranslator personTranslator = new PersonTranslator();
        while (phoneNumberIterator.hasNext())
        {
            PhoneNumber phoneNumber1 = phoneNumberIterator.next();
            PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
            PhoneTypeTranslator phoneTypeTranslator = new PhoneTypeTranslator();
            BeanUtil.copyBeanProperties(phoneNumber1, phoneNumberDto, new ArrayList<>());
            phoneNumberDto.setPhoneTypeDto(phoneTypeTranslator.translateToPhoneTypeDto(phoneNumber1.getIdPhoneType()));
            phoneNumberDto.setUserDto(personTranslator.translateUserToDto(phoneNumber1.getUser()));
            phoneNumberDtos.add(phoneNumberDto);
        }
        return phoneNumberDtos;
    }

    public PhoneNumberDto translateToPhoneNumberDto(PhoneNumber phoneNumber) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        if (phoneNumber == null)
        {
            return phoneNumberDto;
        }
        PersonTranslator personTranslator = new PersonTranslator();
        CMSLogger.info(logger, "PhoneNumberDto:" + phoneNumberDto);
        PhoneTypeTranslator phoneTypeTranslator = new PhoneTypeTranslator();
        BeanUtil.copyBeanProperties(phoneNumber, phoneNumberDto, new ArrayList<>());
        phoneNumberDto.setUserDto(personTranslator.translateUserToDto(phoneNumber.getUser()));
        phoneNumberDto.setPhoneTypeDto(phoneTypeTranslator.translateToPhoneTypeDto(phoneNumber.getIdPhoneType()));
        return phoneNumberDto;
    }

    public PhoneNumber translateDtoToPhoneNumber(PhoneNumberDto phoneNumberDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        PhoneNumber phoneNumber = new PhoneNumber();
        if (phoneNumberDto == null)
        {
            return phoneNumber;
        }
        PersonTranslator personTranslator = new PersonTranslator();
        CMSLogger.info(logger, "PhoneNumberDto:" + phoneNumberDto);
        PhoneTypeTranslator phoneTypeTranslator = new PhoneTypeTranslator();
        BeanUtil.copyBeanProperties(phoneNumberDto, phoneNumber, new ArrayList<>());
        phoneNumber.setIdPhoneType(phoneTypeTranslator.getPhoneTypeId(phoneNumberDto.getPhoneTypeDto()));
        phoneNumber.setUser(personTranslator.translateToPerson(phoneNumberDto.getUserDto()));
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

    public PhoneNumberDto translateToPhoneNumberDto(Integer idPhoneNumber) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
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