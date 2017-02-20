package com.crackers.model;

import java.io.Serializable;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class BaseCategory implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	private Integer				id;
	private Integer				productCode;
	private String				productName;
	private String				productMeasurements;
	private String				productDescription;
	private String				isFancy;
	@Relationship(type = "HAS_MULTIPLE_BRANDS", direction = Relationship.OUTGOING)
	private List<ProductBrand>	productBrand		= Lists.newArrayList();
}