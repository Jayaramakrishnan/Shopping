package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@Data
@NodeEntity(label = "RoleFunctionalAccess")
public class RoleFunctionalAccess implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private Integer				createdBy;
	private Long				createdOn;
	private Integer				updatedBy;
	private Long				updatedOn;
	private Short				isDeleted;
	@Relationship(type = "HAS_FUNCTIONAL_ACCESS", direction = Relationship.OUTGOING)
	private FunctionalAccess	functionalAccess;
	@Relationship(type = "HAS_FUNCTIONAL_RESOURCE", direction = Relationship.OUTGOING)
	private FunctionalResource	functionalResource;
}