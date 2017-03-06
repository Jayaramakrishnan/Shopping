package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "SubCategory")
public class SubCategory implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private Integer				idCategory;
	private String				subCategoryName;
	private Short				isDeleted;
	private Integer				createdBy;
	private Long				createdOn;
	private Integer				updatedBy;
	private Long				updatedOn;
	private Integer				sortOrder;
}