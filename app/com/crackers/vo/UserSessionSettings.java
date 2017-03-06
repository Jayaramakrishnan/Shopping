package com.crackers.vo;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class UserSessionSettings {

	private String	resourceId;
	private String	genericId;
	private String	redirectUrl;
	private String	redirectIdActivity;
}