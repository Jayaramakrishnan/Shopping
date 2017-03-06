package com.crackers.model;

import java.io.Serializable;

import org.hibernate.envers.Audited;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Audited
@NodeEntity
@Data
public class UserCredential implements Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private Long				idUser;
	private String				saltKey;
	private String				hashedKey;
	private Short				isDeleted;
	private Long				createdBy;
	private Long				createdOn;
	private Long				updatedBy;
	private Long				updatedOn;
}