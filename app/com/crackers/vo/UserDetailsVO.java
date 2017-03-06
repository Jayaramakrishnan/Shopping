package com.crackers.vo;

import java.util.List;

import lombok.Data;

@Data
public class UserDetailsVO {

	private String					id;
	private String					name;
	private String					userName;
	private String					bioData;
	private Short					isDeleted;
	private List<ContactDetailsVO>	contactDetailsVos;
	private List<PhoneVO>			phoneNumberVos;
	private List<EmailVO>			emailVos;
	private Long					count;
	private String					oldPassword;
	private String					newPassword;
	private Short					imageIsAvailable;
	private Long					idImageColorCode;
	private String					imageColorCode;
}