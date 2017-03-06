package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "ProductBrand")
public class ProductBrand {

	@GraphId
	private Integer	id;
	private String	brandCode;
	private String	brandName;
	private String	logoPath;
	private Integer	productPrice;
	private Integer	discountRate;		// Interms of Percent
	private Integer	availableQuantity;
	private Integer	quantityToBeShipped;
	private Integer	noOfPieces;
}