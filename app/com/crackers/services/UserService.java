package com.crackers.services;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.dto.UserDto;
import com.crackers.manager.db.UserManager;
import com.crackers.model.User;
import com.crackers.repositories.ImageColorCodeRepository;
import com.crackers.translators.PersonTranslator;

@Component
public class UserService
{

	private Logger						logger	= Logger.getLogger(UserService.class);
	@Resource
	private ImageColorCodeRepository	imageColorCodeRepository;
	@Resource
	private UserManager					userManager;
	@Resource
	private PersonTranslator			personTranslator;

	public Long getUser(String userName)
	{
		return userManager.getUser(userName);
	}

	public UserDto createUser(int idUser, UserDto userDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CMSLogger.info(logger, "Creating an User");
		User user = personTranslator.translateToPerson(userDto);
		User createdUser = userManager.createUser(idUser, user);
		UserDto cDto = personTranslator.translateUserToDto(createdUser);
		UserDto dto = userManager.getUserMasterDto(cDto);
		dto.setImageColorCode(imageColorCodeRepository.getImageColorCode(dto.getIdImageColorCode()));
		return dto;
	}
}