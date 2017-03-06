package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "Category")
public class Category implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private String				categoryName;
	private Short				isDeleted;
	private Integer				sortOrder;
	private Integer				createdBy;
	private Long				createdOn;
	private Integer				updatedBy;
	private Long				updatedOn;
}