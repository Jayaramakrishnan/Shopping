package com.crackers.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FunctionalResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"FUNCTIONAL_RESOURCE\"")
public class FunctionalResource implements java.io.Serializable, Comparable<FunctionalResource>
{

	private static final long		serialVersionUID	= 1L;
	// Fields
	private Integer					idFunctionalResource;
	private String					functions;
	private Integer					createdBy;
	private Timestamp				createdOn;
	
	@Id
	@Column(name = "ID_FUNCTIONAL_RESOURCE", unique = true, nullable = false, length = 11)
	public Integer getIdFunctionalResource()
	{
		return idFunctionalResource;
	}
	
	public void setIdFunctionalResource(Integer idFunctionalResource)
	{
		this.idFunctionalResource = idFunctionalResource;
	}
	
	@Column(name = "FUNCTIONS", length = 45)
	public String getFunctions()
	{
		return functions;
	}
	
	public void setFunctions(String functions)
	{
		this.functions = functions;
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
	
	public int hashCode(){
		return this.idFunctionalResource.hashCode();
	}

	@Override
	public int compareTo(FunctionalResource o) {
		// TODO Auto-generated method stub
		return o.getIdFunctionalResource().compareTo(idFunctionalResource);
	}
	
	
}
