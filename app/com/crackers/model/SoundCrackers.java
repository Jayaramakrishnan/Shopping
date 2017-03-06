package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@NodeEntity(label = "SoundCrackers")
public class SoundCrackers extends BaseCategory {

	private static final long	serialVersionUID	= 1L;
	private String				productType;
	// Big or Small
	private String				walaSize;
}