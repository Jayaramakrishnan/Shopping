/**
 * @author jayaramakrishnanrajagopal
 * @date 08-Feb-2017
 */
package com.crackers.model;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@NodeEntity(label = "Bomb")
public class Bomb extends BaseCategory {

	private static final long	serialVersionUID	= 1L;
	// Like Bullet or Atom Bomb
	private String				bombType;
	private String				bombSize;
}