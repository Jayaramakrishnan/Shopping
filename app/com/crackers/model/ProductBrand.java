package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;

@Data
@NodeEntity(label = "ProductBrand")
public class ProductBrand
{

	@GraphId
	@Property(name = "id")
	private Integer	id;
	@Property(name = "brandCode")
	private String	brandCode;
	@Property(name = "brandName")
	private String	brandName;
	@Property(name = "logoPath")
	private String	logoPath;
	@Property(name = "productPrice")
	private Integer	productPrice;
	@Property(name = "discountRate")
	private Integer	discountRate;		// Interms of Percent
	@Property(name = "availableQuantity")
	private Integer	availableQuantity;
	@Property(name = "quantityToBeShipped")
	private Integer	quantityToBeShipped;
	@Property(name = "noOfPieces")
	private Integer	noOfPieces;
}