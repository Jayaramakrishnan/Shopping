package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "Password")
public class Password implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private Long				idUser;
	private String				encryptText;
	private String				email;
	private String				saltKey;
	private Short				isDeleted;
	private Short				isExpired;
	private Long				createdBy;
	private Long				createdOn;
}