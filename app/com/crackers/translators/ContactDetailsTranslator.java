package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.dto.ContactDetailsDto;
import com.crackers.model.ContactDetails;

@Component
public class ContactDetailsTranslator
{

    private static Logger    logger = Logger.getLogger(ContactDetailsTranslator.class);
    @Resource
    private PersonTranslator personTranslator;

    public ContactDetails translateToContactDetails(ContactDetailsDto contactDetailsDto) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        ContactDetails entityContactDetails = new ContactDetails();
        List<String> properties = new ArrayList<>();
        BeanUtil.copyBeanProperties(contactDetailsDto, entityContactDetails, properties);
        entityContactDetails.setUser(personTranslator.translateToPerson(contactDetailsDto.getUserDto()));
        CMSLogger.info(logger, " Contact Details is translated");
        return entityContactDetails;
    }

    public ContactDetailsDto translateToDto(ContactDetails contactDetails) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if (contactDetails == null)
        {
            return null;
        }
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        List<String> properties = new ArrayList<>();
        BeanUtil.copyBeanProperties(contactDetails, contactDetailsDto, properties);
        contactDetailsDto.setUserDto(personTranslator.translateUserToDto(contactDetails.getUser()));
        CMSLogger.info(logger, " Contact Details is translated");
        return contactDetailsDto;
    }

    public ContactDetailsDto translateToContactDetailsDto(ContactDetails contactDetails) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if (contactDetails == null)
        {
            return null;
        }
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        List<String> properties = new ArrayList<>();
        BeanUtil.copyBeanProperties(contactDetails, contactDetailsDto, properties);
        contactDetailsDto.setUserDto(personTranslator.translateUserToDto(contactDetails.getUser()));
        CMSLogger.info(logger, " Contact Details is translated");
        return contactDetailsDto;
    }

    public List<ContactDetailsDto> translateToDto(List<ContactDetails> contactDetails) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        List<ContactDetailsDto> entityEmails = new ArrayList<>();
        List<ContactDetailsDto> entityEmailsFinal = new ArrayList<>();
        Iterator<ContactDetails> eIterator = contactDetails.iterator();
        while (eIterator.hasNext())
        {
            ContactDetails numberDto = eIterator.next();
            ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
            BeanUtil.copyBeanProperties(numberDto, contactDetailsDto, new ArrayList<>());
            contactDetailsDto.setUserDto(personTranslator.translateUserToDto(numberDto.getUser()));
            entityEmails.add(contactDetailsDto);
        }
        entityEmailsFinal.addAll(entityEmails);
        return entityEmailsFinal;
    }
}