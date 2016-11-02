package com.crackers.dto;

public class UserInfo
{

	private Integer	idUser;
	private String	firstName;
	private String	fullName;
	private String	lastName;
	private String	userName;
	private Boolean	userPreference;
	private Boolean	isMailed	= false;

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public Integer getIdUser()
	{
		return idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void seteMail(String userName)
	{
		setUserName(userName);
	}

	public Boolean getUserPreference()
	{
		return userPreference;
	}

	public void setUserPreference(Boolean userPreference)
	{
		this.userPreference = userPreference;
	}

	public Boolean getIsMailed()
	{
		return isMailed;
	}

	public void setIsMailed(Boolean isMailed)
	{
		this.isMailed = isMailed;
	}
}
