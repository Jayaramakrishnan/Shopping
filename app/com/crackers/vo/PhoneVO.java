package com.crackers.vo;

import lombok.Data;

@Data
public class PhoneVO {

	private Long	id;
	private String	phoneNumber;
	private Short	isDeleted;
	private Long 	idPhoneType;
	private String	phoneType;
}