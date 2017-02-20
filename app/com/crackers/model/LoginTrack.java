package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "LoginTrack")
public class LoginTrack implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private Long				idUser;
	private String				email;
	private String				userDevice;
	private String				userAgent;
	private String				clientIp;
	private Long				loggedOnTime;
	private Long				loggedOut;
	private String				sessionToken;
	private Short				isSuccess;
	private Short				isSessionExpired;
	private Long				createdBy;
	private Long				createdOn;
}