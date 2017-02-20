package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "PhoneNumber")
public class PhoneNumber implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private String				phoneNumberValue;
	private Integer				phoneNumberSource	= 1;
	private Integer				idPhoneType;
	private Short				isDeleted			= 0;
	private Long				createdBy;
	private Long				createdOn;
	private Long				updatedBy;
	private Long				updatedOn;
}