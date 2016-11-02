package com.crackers.manager.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crackers.common.CommonConstants;
import com.crackers.common.DateStringUtil;
import com.crackers.common.RestUrlAttribute;
import com.crackers.dto.UserDto;
import com.crackers.model.ImageColorCode;
import com.crackers.model.Role;
import com.crackers.model.User;
import com.crackers.model.UserSource;
import com.crackers.model.UserState;
import com.crackers.repositories.ImageColorCodeRepository;
import com.crackers.repositories.RoleRepository;
import com.crackers.repositories.UserRepository;
import com.crackers.repositories.UserRoleRepository;
import com.crackers.repositories.UserSourceRepository;
import com.crackers.repositories.UserStateRepository;
import com.crackers.translators.PersonTranslator;
import com.crackers.translators.RoleTranslator;
import com.crackers.translators.UserSourceTranslator;
import com.crackers.translators.UserStateTranslator;

@Component
public class UserManager
{

	@Resource
	private ImageColorCodeRepository	imageColorCodeRepository;
	@Resource
	private UserRepository				userRepository;
	@Resource
	private UserRoleRepository			userRoleRepository;
	@Resource
	private DateStringUtil				dateStringUtil;
	@Resource
	private RoleRepository				roleRepository;
	@Resource
	private UserSourceRepository		userSourceRepository;
	@Resource
	private UserStateRepository			userStateRepository;
	@Resource
	private RoleTranslator				roleTranslator;
	@Resource
	private UserSourceTranslator		userSourceTranslator;
	@Resource
	private UserStateTranslator			userStateTranslator;
	@Resource
	private PersonTranslator			personTranslator;

	public UserDto getUserMasterDto(UserDto userDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if (userDto.getRoleDto() != null && userDto.getRoleDto().getIdRole() != null)
		{
			Role category = roleRepository.findOne(userDto.getRoleDto().getIdRole());
			userDto.setRoleDto(roleTranslator.translateToRoleDto(category));
		}
		if (userDto.getUserSourceDto() != null && userDto.getUserSourceDto().getIdSource() != null)
		{
			UserSource category = userSourceRepository.findOne(userDto.getUserSourceDto().getIdSource());
			userDto.setUserSourceDto(userSourceTranslator.translateToUserSourceDto(category));
		}
		if (userDto.getUserStateDto() != null && userDto.getUserStateDto().getIdUserState() != null)
		{
			UserState category = userStateRepository.findOne(userDto.getUserStateDto().getIdUserState());
			userDto.setUserStateDto(userStateTranslator.translateToUserStateDto(category));
		}
		return userDto;
	}

	public User createUser(Integer idUser, User user)
	{
		Integer random = (int) (Math.random() * 17 + 1);
		ImageColorCode imageColorCode = imageColorCodeRepository.findOne(random);
		Timestamp ts = dateStringUtil.getCurrentTimestamp();
		user.setIsDeleted((short) 0);
		user.setIdUserState(com.crackers.enums.UserState.getCode(com.crackers.enums.UserState.ACTIVE));
		String fullName = RestUrlAttribute.EMPTY_QUOTES;
		if (user.getFirstName() != null)
		{
			fullName = user.getFirstName();
		}
		if (user.getLastName() != null && fullName.length() > CommonConstants.EMPTY_LIST)
		{
			fullName = fullName.concat(" ").concat(user.getLastName());
		}
		else if (user.getLastName() != null)
		{
			fullName = user.getLastName();
		}
		user.setFullName(fullName);
		if (user.getIdImageColorCode() == null && imageColorCode != null)
		{
			user.setIdImageColorCode(imageColorCode.getIdImageColorCode());
		}
		user.setCreatedBy(idUser);
		user.setCreatedOn(ts);
		user.setLastUpdatedOn(ts);
		return userRepository.saveAndFlush(user);
	}

	public Long getUser(String userName)
	{
		return userRepository.getUser(userName);
	}
}