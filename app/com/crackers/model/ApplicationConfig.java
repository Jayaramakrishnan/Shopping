package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "ApplicationConfig")
public class ApplicationConfig implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private String				configKey;
	private String				configValue;
	private Integer				createdBy;
	private Long				createdOn;
}