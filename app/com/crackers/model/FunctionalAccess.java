package com.crackers.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FunctionalAccess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"FUNCTIONAL_ACCESS\"")
public class FunctionalAccess implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idFunctionalAccess;
	private String				functionAccess;
	private Integer				createdBy;
	private Timestamp			createdOn;

	@Id
	@Column(name = "ID_FUNCTIONAL_ACCESS", unique = true, nullable = false, length = 11)
	public Integer getIdFunctionalAccess()
	{
		return idFunctionalAccess;
	}

	public void setIdFunctionalAccess(Integer idFunctionalAccess)
	{
		this.idFunctionalAccess = idFunctionalAccess;
	}

	@Column(name = "FUNCTION_ACCESS", length = 128)
	public String getFunctionAccess()
	{
		return functionAccess;
	}

	public void setFunctionAccess(String functionAccess)
	{
		this.functionAccess = functionAccess;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 11)
	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_ON", nullable = false)
	public Timestamp getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}
}
