package com.crackers.dto;

public class RoleDto
{

	private Integer	idRole;
	private String	role;
	private Short	isDeleted;

	public Integer getIdRole()
	{
		return idRole;
	}

	public void setIdRole(Integer idRole)
	{
		this.idRole = idRole;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public Short getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}
}