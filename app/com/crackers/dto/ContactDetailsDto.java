package com.crackers.dto;

import lombok.Data;

@Data
public class ContactDetailsDto {

	private Long	idContactDetails;
	private String	street;
	private String	city;
	private String	state;
	private String	pincode;
	private Short	isDeleted;
}