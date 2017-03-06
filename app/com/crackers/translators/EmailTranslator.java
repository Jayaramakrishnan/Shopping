package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CrackersLogger;
import com.crackers.dto.EmailDto;
import com.crackers.model.Email;

@Component
public class EmailTranslator {

	private Logger logger = Logger.getLogger(EmailTranslator.class);

	public List<EmailDto> translateListToEmailDto(List<Email> emails) throws InvocationTargetException {
		List<EmailDto> emailDtos = new ArrayList<>();
		if (emails == null) {
			return emailDtos;
		}
		Iterator<Email> emailIterator = emails.iterator();
		while (emailIterator.hasNext()) {
			Email email1 = emailIterator.next();
			EmailDto emailDto = new EmailDto();
			BeanUtil.copyBeanProperties(email1, emailDto, new ArrayList<>());
			emailDtos.add(emailDto);
		}
		return emailDtos;
	}
	
	public List<Email> translateDtoListToEmail(List<EmailDto> emailDts) throws InvocationTargetException {
		List<Email> emails = new ArrayList<>();
		if (emailDts == null) {
			return emails;
		}
		Iterator<EmailDto> emailIterator = emailDts.iterator();
		while (emailIterator.hasNext()) {
			EmailDto emailDto = emailIterator.next();
			Email email = new Email();
			BeanUtil.copyBeanProperties(emailDto, email, new ArrayList<>());
			emails.add(email);
		}
		return emails;
	}

	public EmailDto translateToEmailDto(Email email) throws InvocationTargetException {
		EmailDto emailDto = new EmailDto();
		if (email == null) {
			return emailDto;
		}
		CrackersLogger.info(logger, "EmailDto:" + emailDto);
		BeanUtil.copyBeanProperties(email, emailDto, new ArrayList<>());
		return emailDto;
	}

	public Email translateDtoToEmail(EmailDto emailDto) throws InvocationTargetException {
		Email email = new Email();
		if (emailDto == null) {
			return email;
		}
		CrackersLogger.info(logger, "EmailDto:" + emailDto);
		BeanUtil.copyBeanProperties(emailDto, email, new ArrayList<>());
		return email;
	}

	public Long getEmailId(EmailDto emailDto) {
		if (emailDto == null) {
			return null;
		}
		return emailDto.getId();
	}

	public EmailDto translateToEmailDto(Long idEmail) throws InvocationTargetException {
		if (idEmail == null) {
			return null;
		}
		EmailDto emailDto = new EmailDto();
		emailDto.setId(idEmail);
		return emailDto;
	}
}