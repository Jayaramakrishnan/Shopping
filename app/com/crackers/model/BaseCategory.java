package com.crackers.model;

import java.io.Serializable;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class BaseCategory implements Serializable {

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Long				id;
	private String				categoryCode;
	private String				crackerCode;
	private String				crackerName;
	private String				crackerMeasurements;
	private String				crackerDescription;
	private String				isFancy;
	@Relationship(type = "HAS_MULTIPLE_BRANDS", direction = Relationship.OUTGOING)
	private List<CrackerBrand>	crackersBrand		= Lists.newArrayList();
}