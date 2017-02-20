package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "EmailTemplate")
public class EmailTemplate implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private String				subject;
	private String				body;
	private Short				isDeleted;
	private Integer				createdBy;
	private Long				createdOn;
	private Integer				updatedBy;
	private Long				updatedOn;
}