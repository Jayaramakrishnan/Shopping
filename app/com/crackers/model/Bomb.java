/**
 * @author jayaramakrishnanrajagopal
 * @date 08-Feb-2017
 */
package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@NodeEntity(label = "Bomb")
public class Bomb extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	// Like Bullet or Atom Bomb
	@Property(name = "bombType")
	private String				bombType;
	@Property(name = "bombSize")
	private String				bombSize;
}