package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "Email")
public class Email implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private String				emailValue;
	private Integer				emailSource			= 1;
	private Short				isPrimary			= 1;
	private Short				isDeleted;
	private Long				createdBy;
	private Long				createdOn;
	private Long				updatedBy;
	private Long				updatedOn;
}