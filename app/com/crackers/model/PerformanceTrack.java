package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "PerformanceTrack")
public class PerformanceTrack implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private Integer				idUser;
	private String				className;
	private String				method;
	private Long				threadId;
	private Integer				lineNumber;
	private Long				startTime;
	private Long				endTime;
}