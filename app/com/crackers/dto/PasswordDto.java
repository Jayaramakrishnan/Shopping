package com.crackers.dto;

public class PasswordDto
{

	private Integer	idPassword;
	private String	encryptText;
	private String	email;
	private Integer	idUser;
	private String	saltKey;
	private Integer	idFlag;
	private Short	isDeleted;
	
	public Integer getIdPassword()
	{
		return idPassword;
	}

	public void setIdPassword(Integer idPassword)
	{
		this.idPassword = idPassword;
	}

	public String getEncryptText()
	{
		return encryptText;
	}

	public void setEncryptText(String encryptText)
	{
		this.encryptText = encryptText;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getIdUser()
	{
		return idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	public String getSaltKey()
	{
		return saltKey;
	}

	public void setSaltKey(String saltKey)
	{
		this.saltKey = saltKey;
	}

	public Integer getIdFlag()
	{
		return idFlag;
	}

	public void setIdFlag(Integer idFlag)
	{
		this.idFlag = idFlag;
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