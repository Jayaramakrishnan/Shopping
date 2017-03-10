package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;

@Data
@NodeEntity(label = "UserCredentialAud")
public class UserCredentialAud implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	@Property(name = "idRev")
	private Long				idRev;
	private Long				idUserCredential;
	private Short				revtype;
	private Long				idUser;
	private String				saltKey;
	private String				hashedKey;
	private Short				isDeleted;
	private Long				createdBy;
	private Long				createdOn;
	private Long				updatedBy;
	private Long				updatedOn;
}