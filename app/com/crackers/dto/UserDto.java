package com.crackers.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {

	private String					id;
	private String					name;
	private String					userName;
	private String					bioData;
	private Short					isDeleted;
	private List<ContactDetailsDto>	contactDetailsDtos;
	private List<PhoneNumberDto>	phoneNumberDtos;
	private List<EmailDto>			emailDtos;
	private List<String>			changedList;
	private Long					count;
	private String					oldPassword;
	private String					newPassword;
	private Short					imageIsAvailable;
	private Long					idImageColorCode;
	private String					imageColorCode;
	private RoleDto					roleDto;
	private ImageDto				imageDto;
}