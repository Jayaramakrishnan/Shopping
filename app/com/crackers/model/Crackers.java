package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@Data
@NodeEntity(label = "Crackers")
public class Crackers {

	@GraphId
	private Integer			id;
	@Relationship(type = "HAS_CATEGORY", direction = Relationship.OUTGOING)
	private SoundCrackers	soundCrackers;
	@Relationship(type = "HAS_CATEGORY", direction = Relationship.OUTGOING)
	private Sparkles		sparkles;
}