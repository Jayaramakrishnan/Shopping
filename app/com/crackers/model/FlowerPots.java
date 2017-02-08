/**
 * @author jayaramakrishnanrajagopal
 * @date 07-Feb-2017
 */
package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@NodeEntity(label = "FlowerPots")
public class FlowerPots extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	@Property(name = "potSize")
	private String				potSize;
	@Property(name = "potType")
	private String				potType;					// Single or Multiple Color
	@Property(name = "potColor")
	private String				potColor;
}