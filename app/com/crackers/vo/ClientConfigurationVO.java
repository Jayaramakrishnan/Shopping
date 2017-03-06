package com.crackers.vo;

import lombok.Data;

@Data
public class ClientConfigurationVO {

	private String	version;
	private String	restBaseUrl;
	private String	sessionTimeout;
	private String	year;
	private String	applicationNameInLogin;
	private Short	isApplicationLogoAvailable;
	private String	applicationNameInheader;
	private String	applicationTitleName;
	private String	dateFormat;
	private Integer	autoSaveTimeout;
	private String	isTLSOn;
	private Integer	isSendEmailOn;
	private Integer previousPasswordCheckCount;
}