package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

@Data
@NodeEntity(label = "Products")
public class Products
{

	@GraphId
	@Property(name = "idProduct")
	private Integer	idProduct;
	@Relationship(type = "WITH_IN", direction = Relationship.OUTGOING)
	private Crackers	crackers;
}