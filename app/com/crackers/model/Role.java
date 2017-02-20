package com.crackers.model;

import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
@NodeEntity(label = "Role")
public class Role implements java.io.Serializable
{

	private static final long			serialVersionUID		= 1L;
	@GraphId
	private Integer						id;
	private String						role;
	private Short						isDeleted;
	private Integer						createdBy;
	private Long						createdOn;
	private Integer						updatedBy;
	private Long						updatedOn;
	@Relationship(type = "HAS_ROLE_FUNCTIONAL_ACCESS", direction = Relationship.OUTGOING)
	private List<RoleFunctionalAccess>	roleFunctionalAccess	= Lists.newArrayList();
}