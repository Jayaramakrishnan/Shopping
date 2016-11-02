package com.crackers.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PhoneType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"PHONE_TYPE\"")
public class PhoneType implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer		idPhoneType = 1;
	private String		phoneType;
	private Integer		createdBy;
	private Timestamp	createdOn;

	// Constructors
	/** default constructor */
	public PhoneType() {
	}

	/** full constructor */
	public PhoneType(Integer idPhoneType, String phoneType, Integer createdBy, Timestamp createdOn) {
		this.idPhoneType = idPhoneType;
		this.phoneType = phoneType;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	// Property accessors
	@Id
	@Column(name = "ID_PHONE_TYPE", unique = true, nullable = false, length = 11)
	public Integer getIdPhoneType()
	{
		return this.idPhoneType;
	}

	public void setIdPhoneType(Integer idPhoneType)
	{
		this.idPhoneType = idPhoneType;
	}

	@Column(name = "PHONE_TYPE", nullable = false, length = 128)
	public String getPhoneType()
	{
		return this.phoneType;
	}

	public void setPhoneType(String phoneType)
	{
		this.phoneType = phoneType;
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