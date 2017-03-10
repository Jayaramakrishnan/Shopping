package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@Data
@NodeEntity(label = "Products")
public class Products {

	@GraphId
	private Long		id;
	@Relationship(type = "WITH_IN", direction = Relationship.OUTGOING)
	private Crackers	crackers;
}