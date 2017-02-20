package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@NodeEntity(label = "Chakkars")
public class Chakkars extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	private String				chakkarSize;
}