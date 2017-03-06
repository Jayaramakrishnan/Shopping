package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.common.CrackersLogger;
import com.crackers.dto.UserDto;
import com.crackers.model.User;
import com.crackers.util.CryptoBinderUtil;

@Component
public class PersonTranslator {

	private static Logger			logger	= Logger.getLogger(PersonTranslator.class);

	public User translateToPerson(UserDto userDto) throws InvocationTargetException {
		User user = new User();
		if (userDto == null) {
			return user;
		}
		CrackersLogger.info(logger, "Iduser in Person Translator before decrypting: " + userDto.getId());
		List<String> properties = new ArrayList<>();
		BeanUtil.copyBeanProperties(userDto, user, properties);
		if (userDto.getId() != null) {
			user.setId(CryptoBinderUtil.getDecryptId(userDto.getId()));
		}
		return user;
	}

	public UserDto translateUserToDto(User user) throws InvocationTargetException {
		if (user == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		BeanUtil.copyBeanProperties(user, userDto, new ArrayList<>());
		if (user.getId() != null) {
			userDto.setId(CryptoBinderUtil.getEncryptId(user.getId().toString()));
		}
		return userDto;
	}
}