package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "FunctionalResource")
public class FunctionalResource implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private String				functions;
	private Integer				createdBy;
	private Long				createdOn;
}