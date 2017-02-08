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
@NodeEntity(label = "Pencils")
public class Pencils extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	@Property(name = "pencilSize")
	private String				pencilSize;
	@Property(name = "pencilColor")
	private String				pencilColor;
}