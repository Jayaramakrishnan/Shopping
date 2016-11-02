package com.crackers.dto;

public class PhoneNumberDto
{

	private Integer			idPhoneNumber;
	private UserDto			userDto;
	private String			phoneNumber;
	private PhoneTypeDto	phoneTypeDto;
	private Short			isDeleted;
	private Integer			phoneNumberSource;

	public Integer getIdPhoneNumber()
	{
		return idPhoneNumber;
	}

	public void setIdPhoneNumber(Integer idPhoneNumber)
	{
		this.idPhoneNumber = idPhoneNumber;
	}

	public UserDto getUserDto()
	{
		return userDto;
	}

	public void setUserDto(UserDto userDto)
	{
		this.userDto = userDto;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public Short getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	public Integer getPhoneNumberSource()
	{
		return phoneNumberSource;
	}

	public void setPhoneNumberSource(Integer phoneNumberSource)
	{
		this.phoneNumberSource = phoneNumberSource;
	}

	public PhoneTypeDto getPhoneTypeDto()
	{
		return phoneTypeDto;
	}

	public void setPhoneTypeDto(PhoneTypeDto phoneTypeDto)
	{
		this.phoneTypeDto = phoneTypeDto;
	}
}