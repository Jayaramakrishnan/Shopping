package com.crackers.dto;

import lombok.Data;

@Data
public class EmailDto {

	private Long	id;
	private Long	idUser;
	private String	email;
	private Short	isDeleted;
	private Short	isPrimary;
}