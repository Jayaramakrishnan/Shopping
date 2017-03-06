package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "Email")
public class Email implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private Long				idUser;
	private String				email;
	private Short				isPrimary;
	private Short				isDeleted;
	private Long				createdBy;
	private Long				createdOn;
	private Long				updatedBy;
	private Long				updatedOn;
}