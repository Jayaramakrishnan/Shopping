package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "FunctionalAccess")
public class FunctionalAccess implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private String				functionAccess;
	private Integer				createdBy;
	private Long				createdOn;
}