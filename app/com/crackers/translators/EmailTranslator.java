package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CMSLogger;
import com.crackers.dto.EmailDto;
import com.crackers.model.Email;

@Component
public class EmailTranslator
{

	private Logger	logger	= Logger.getLogger(EmailTranslator.class);

	public List<EmailDto> translateListToEmailDto(List<Email> emails) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		List<EmailDto> emailDtos = new ArrayList<>();
		if (emails == null)
		{
			return emailDtos;
		}
		Iterator<Email> emailIterator = emails.iterator();
		PersonTranslator personTranslator = new PersonTranslator();
		while (emailIterator.hasNext())
		{
			Email email1 = emailIterator.next();
			EmailDto emailDto = new EmailDto();
			BeanUtil.copyBeanProperties(email1, emailDto, new ArrayList<>());
			emailDto.setUserDto(personTranslator.translateUserToDto(email1.getUser()));
			emailDtos.add(emailDto);
		}
		return emailDtos;
	}

	public EmailDto translateToEmailDto(Email email) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		EmailDto emailDto = new EmailDto();
		if (email == null)
		{
			return emailDto;
		}
		PersonTranslator personTranslator = new PersonTranslator();
		CMSLogger.info(logger, "EmailDto:" + emailDto);
		BeanUtil.copyBeanProperties(email, emailDto, new ArrayList<>());
		emailDto.setUserDto(personTranslator.translateUserToDto(email.getUser()));
		return emailDto;
	}

	public Email translateDtoToEmail(EmailDto emailDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Email email = new Email();
		if (emailDto == null)
		{
			return email;
		}
		CMSLogger.info(logger, "EmailDto:" + emailDto);
		PersonTranslator personTranslator = new PersonTranslator();
		BeanUtil.copyBeanProperties(emailDto, email, new ArrayList<>());
		email.setUser(personTranslator.translateToPerson(emailDto.getUserDto()));
		return email;
	}

	public Integer getEmailId(EmailDto emailDto)
	{
		if (emailDto == null)
		{
			return null;
		}
		return emailDto.getIdEmail();
	}

	public EmailDto translateToEmailDto(Integer idEmail) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if (idEmail == null)
		{
			return null;
		}
		EmailDto emailDto = new EmailDto();
		emailDto.setIdEmail(idEmail);
		return emailDto;
	}
}