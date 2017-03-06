package com.crackers.dto;

import lombok.Data;

@Data
public class LoginTrackDto {

	private Integer	idLoginTrack;
	private Long	idUser;
	private String	userDevice;
	private String	userAgent;
	private String	email;
	private String	clientIp;
	private Long	loggedOnTime;
	private Long	loggedOut;
	private String	sessionToken;
	private Short	isSuccess;
	private Short	isSessionExpired;
	private Long	createdBy;
	private Long	createdOn;
}