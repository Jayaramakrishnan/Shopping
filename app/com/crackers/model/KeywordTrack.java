package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "KeywordTrack")
public class KeywordTrack implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private String				form;
	private String				keyword;
	private Integer				idUser;
	private Integer				count;
	private String				noOfMatches;
	private String				exception;
	private Integer				createdBy;
	private Long				createdOn;
	private Integer				updatedBy;
	private Long				updatedOn;
	private String				url;
}