package com.crackers.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crackers.dto.UserDto;
import com.crackers.manager.db.AuthenticationManager;

@Component
public class AutheticationService
{

	@Resource
	private AuthenticationManager	authenticationManager;

	public Object validate(UserDto userDto, String secrat) throws Exception
	{
		return authenticationManager.validate(userDto, secrat);
	}
}