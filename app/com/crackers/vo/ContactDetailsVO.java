package com.crackers.vo;

import lombok.Data;

@Data
public class ContactDetailsVO {

	private Integer	idContactDetails;
	private String	street;
	private String	city;
	private String	state;
	private String	pincode;
	private Short	isDeleted;
}