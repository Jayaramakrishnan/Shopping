package com.crackers.model;

import java.io.Serializable;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class BaseCategory implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	@Property(name = "id")
	private Integer				id;
	@Property(name = "productCode")
	private Integer				productCode;
	@Property(name = "productName")
	private String				productName;
	@Property(name = "productMeasurements")
	private String				productMeasurements;
	@Property(name = "productDescription")
	private String				productDescription;
	@Property(name = "isFancy")
	private String				isFancy;
	@Relationship(type = "HAS_MULTIPLE_BRANDS", direction = Relationship.OUTGOING)
	private List<ProductBrand>	productBrand		= Lists.newArrayList();
}