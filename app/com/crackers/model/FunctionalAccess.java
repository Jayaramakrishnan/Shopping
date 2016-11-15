package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * FunctionalAccess entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "FunctionalAccess")
public class FunctionalAccess implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idFunctionalAccess;
	private String				functionAccess;
	private Integer				createdBy;
	private Timestamp			createdOn;

	@GraphId
	@Property(name = "idFunctionalAccess")
	public Integer getIdFunctionalAccess()
	{
		return idFunctionalAccess;
	}

	public void setIdFunctionalAccess(Integer idFunctionalAccess)
	{
		this.idFunctionalAccess = idFunctionalAccess;
	}

	@Property(name = "functionAccess")
	public String getFunctionAccess()
	{
		return functionAccess;
	}

	public void setFunctionAccess(String functionAccess)
	{
		this.functionAccess = functionAccess;
	}

	@Property(name = "createdBy")
	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	@Property(name = "createdOn")
	public Timestamp getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}
}
