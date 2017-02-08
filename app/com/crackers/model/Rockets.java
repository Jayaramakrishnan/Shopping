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
@NodeEntity(label = "Rockets")
public class Rockets extends BaseCategory
{

	private static final long	serialVersionUID	= 1L;
	// Whistle or Bomb or Parachute or Comets
	@Property(name = "rocketType")
	private String				rocketType;		
	@Property(name = "rocketColor")
	private String				rocketColor;
	// Bursting like an Umbrela or with Sparkles and also about how much feet will it go and burst kind of stuffs
	@Property(name = "additionalDescription")
	private String				additionalDescription;
}