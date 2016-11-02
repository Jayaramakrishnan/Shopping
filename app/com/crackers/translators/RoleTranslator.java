package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.dto.RoleDto;
import com.crackers.model.Role;

@Component
public class RoleTranslator
{

	public List<RoleDto> translateListToRoleDto(List<Role> roles) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		List<RoleDto> roleDtos = new ArrayList<>();
		if (roles == null)
		{
			return roleDtos;
		}
		Iterator<Role> roleIterator = roles.iterator();
		while (roleIterator.hasNext())
		{
			Role role1 = roleIterator.next();
			RoleDto roleDto = new RoleDto();
			BeanUtil.copyBeanProperties(role1, roleDto, new ArrayList<>());
			roleDtos.add(roleDto);
		}
		return roleDtos;
	}

	public RoleDto translateToRoleDto(Role role) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		RoleDto roleDto = new RoleDto();
		if (role == null)
		{
			return roleDto;
		}
		BeanUtil.copyBeanProperties(role, roleDto, new ArrayList<>());
		return roleDto;
	}

	public Role translateDtoToRole(RoleDto roleDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Role role = new Role();
		if (roleDto == null)
		{
			return role;
		}
		BeanUtil.copyBeanProperties(roleDto, role, new ArrayList<>());
		return role;
	}

	public Integer getRoleId(RoleDto roleDto)
	{
		if (roleDto == null)
		{
			return null;
		}
		return roleDto.getIdRole();
	}

	public RoleDto translateToRoleDto(Integer idRole) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if (idRole == null)
		{
			return null;
		}
		RoleDto roleDto = new RoleDto();
		roleDto.setIdRole(idRole);
		return roleDto;
	}
}