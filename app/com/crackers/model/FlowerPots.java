/**
 * @author jayaramakrishnanrajagopal
 * @date 07-Feb-2017
 */
package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@NodeEntity(label = "FlowerPots")
public class FlowerPots extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	private String				potSize;
	private String				potType;					// Single or Multiple Color
	private String				potColor;
}