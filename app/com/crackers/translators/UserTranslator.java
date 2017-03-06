package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CrackersLogger;
import com.crackers.dto.UserDto;
import com.crackers.model.User;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserTranslator {

	private static Logger logger = Logger.getLogger(UserTranslator.class);

	public UserDto translateToUserDto(User user) throws InvocationTargetException {
		CrackersLogger.info(logger, "Inside translateToUserDto");
		UserDto userDto = new UserDto();
		/*
		 * Checking whether the user object is null.
		 */
		if (user == null) {
			return userDto;
		}
		/*
		 * Copying model parameters to dto parameters.
		 */
		BeanUtil.copyBeanProperties(user, userDto, new ArrayList<>());
		if (user.getId() != null) {
			userDto.setId(CryptoBinderUtil.getEncryptId(user.getId().toString()));
		}
		return userDto;
	}

	public User translateToUser(Long idUser) throws InvocationTargetException {
		/*
		 * Checking whether the idUser is null.
		 */
		if (idUser == null) {
			return null;
		}
		/*
		 * Copying model parameters to dto parameters.
		 */
		User user = new User();
		user.setId(idUser);
		return user;
	}
}