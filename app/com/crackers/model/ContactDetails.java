package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "ContactDetails")
public class ContactDetails implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private Integer				streetSource;
	private String				street;
	private Integer				citySource;
	private String				city;
	private Integer				stateSource;
	private String				state;
	private Integer				pincodeSource;
	private String				pincode;
	private Integer				websiteSource;
	private String				website;
	private Short				isDeleted;
	private Long				createdBy;
	private Long				createdOn;
	private Long				updatedBy;
	private Long				updatedOn;
}