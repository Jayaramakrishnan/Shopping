package com.crackers.dto;

import lombok.Data;

@Data
public class PhoneNumberDto {

	private Long			id;
	private String			phoneNumber;
	private PhoneTypeDto	phoneTypeDto;
	private Short			isDeleted;
}