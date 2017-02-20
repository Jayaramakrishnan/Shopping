package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@NodeEntity(label = "Sparkles")
public class Sparkles extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	private String				sparkleColor;
}