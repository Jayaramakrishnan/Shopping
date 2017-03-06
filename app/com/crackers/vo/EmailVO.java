package com.crackers.vo;

import lombok.Data;

@Data
public class EmailVO {

	private Long	id;
	private Long	idUser;
	private String	email;
	private Short	isDeleted;
	private Short	isPrimary;
}