package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * UserSource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"USER_SOURCE\"")
@Audited
public class UserSource implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	private Integer				idSource;
	private String				source;
	private Integer				createdBy;
	private Timestamp			createdOn;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_USER_SOURCE", unique = true, nullable = false, length = 11)
	public Integer getIdSource()
	{
		return this.idSource;
	}

	public void setIdSource(Integer idSource)
	{
		this.idSource = idSource;
	}

	@Column(name = "SOURCE", nullable = false, length = 128)
	public String getSource()
	{
		return this.source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 11)
	public Integer getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_ON", nullable = false)
	public Timestamp getCreatedOn()
	{
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}
}