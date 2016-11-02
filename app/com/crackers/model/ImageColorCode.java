package com.crackers.model;

// default package
import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ImageColorCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"IMAGE_COLOR_CODE\"")
public class ImageColorCode implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idImageColorCode;
	private String				imageColorCode;
	private Short				isDeleted;
	private Integer				createdBy;
	private Timestamp			createdOn;

	// Constructors
	/** default constructor */
	public ImageColorCode() {
	}

	/** minimal constructor */
	public ImageColorCode(String imageColorCode, Short isDeleted) {
		this.imageColorCode = imageColorCode;
		this.isDeleted = isDeleted;
	}

	/** full constructor */
	public ImageColorCode(String imageColorCode, Short isDeleted, Integer createdBy, Timestamp createdOn) {
		this.imageColorCode = imageColorCode;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_IMAGE_COLOR_CODE", unique = true, nullable = false, length = 11)
	public Integer getIdImageColorCode()
	{
		return this.idImageColorCode;
	}

	public void setIdImageColorCode(Integer idImageColorCode)
	{
		this.idImageColorCode = idImageColorCode;
	}

	@Column(name = "IMAGE_COLOR_CODE", nullable = false, length = 256)
	public String getImageColorCode()
	{
		return this.imageColorCode;
	}

	public void setImageColorCode(String imageColorCode)
	{
		this.imageColorCode = imageColorCode;
	}

	@Column(name = "IS_DELETED", nullable = false, length = 6, columnDefinition = "smallint default 0")
	public Short getIsDeleted()
	{
		return this.isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	@Column(name = "CREATED_BY", length = 11, nullable = false)
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