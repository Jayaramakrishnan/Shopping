package com.crackers.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity(label = "CrackerBrand")
public class CrackerBrand {

	@GraphId
	private Long	id;
	private String	brandCode;
	private String	brandName;
	private String	logoPath;
	private Double	crackerPrice;
	private Double	discountRate;		// Interms of Percent
	private Long	availableQuantity;
	private Long	quantityToBeShipped;
	private Long	noOfPieces;
}