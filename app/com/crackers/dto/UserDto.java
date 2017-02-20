package com.crackers.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto
{

	private String					idUser;
	private RoleDto					roleDto;
	private String					userName;
	private ImageDto				imageDto;
	private UserSourceDto			userSourceDto;
	private Short					isDeleted;
	private String					uniqueId;
	private String					title;
	private String					bioData;
	private List<ContactDetailsDto>	contactDetailsDtos;
	private List<PhoneNumberDto>	phoneNumberDtos;
	private List<EmailDto>			emailDtos;
	private List<String>			changedList;
	private UserStateDto			userStateDto;
	private Long					count;
	private String					oldPassword;
	private String					newPassword;
	private String					email;
	private String					imageColorCode;
	private Integer					idImageColorCode;
	private Short					isRegistered;
	private Short					imageIsAvailable;
}