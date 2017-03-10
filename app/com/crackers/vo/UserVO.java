/**
 * 
 * @author natarra
 *
 */
package com.crackers.vo;

import lombok.Data;

@Data
public class UserVO {

	private String	idUser;
	private String	userName;
	private Long	idRole;
	private String	role;
	private String	imageColorCode;
}