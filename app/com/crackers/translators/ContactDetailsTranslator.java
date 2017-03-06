package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CrackersLogger;
import com.crackers.dto.ContactDetailsDto;
import com.crackers.model.ContactDetails;

@Component
public class ContactDetailsTranslator {

	private static Logger logger = Logger.getLogger(ContactDetailsTranslator.class);

	public ContactDetails translateToContactDetails(ContactDetailsDto contactDetailsDto) throws InvocationTargetException {
		ContactDetails entityContactDetails = new ContactDetails();
		List<String> properties = new ArrayList<>();
		BeanUtil.copyBeanProperties(contactDetailsDto, entityContactDetails, properties);
		CrackersLogger.info(logger, " Contact Details is translated");
		return entityContactDetails;
	}

	public ContactDetailsDto translateToDto(ContactDetails contactDetails) throws InvocationTargetException {
		if (contactDetails == null) {
			return null;
		}
		ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
		List<String> properties = new ArrayList<>();
		BeanUtil.copyBeanProperties(contactDetails, contactDetailsDto, properties);
		CrackersLogger.info(logger, " Contact Details is translated");
		return contactDetailsDto;
	}

	public ContactDetailsDto translateToContactDetailsDto(ContactDetails contactDetails) throws InvocationTargetException {
		if (contactDetails == null) {
			return null;
		}
		ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
		List<String> properties = new ArrayList<>();
		BeanUtil.copyBeanProperties(contactDetails, contactDetailsDto, properties);
		CrackersLogger.info(logger, " Contact Details is translated");
		return contactDetailsDto;
	}

	public List<ContactDetailsDto> translateToDto(List<ContactDetails> contactDetails) throws InvocationTargetException {
		List<ContactDetailsDto> entityEmails = new ArrayList<>();
		List<ContactDetailsDto> entityEmailsFinal = new ArrayList<>();
		Iterator<ContactDetails> eIterator = contactDetails.iterator();
		while (eIterator.hasNext()) {
			ContactDetails numberDto = eIterator.next();
			ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
			BeanUtil.copyBeanProperties(numberDto, contactDetailsDto, new ArrayList<>());
			entityEmails.add(contactDetailsDto);
		}
		entityEmailsFinal.addAll(entityEmails);
		return entityEmailsFinal;
	}
}