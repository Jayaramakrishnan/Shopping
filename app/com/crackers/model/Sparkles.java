package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@NodeEntity(label = "Sparkles")
public class Sparkles extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	@Property(name = "sparkleColor")
	private String				sparkleColor;
}