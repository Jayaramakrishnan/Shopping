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
 * Category entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"CATEGORY\"")
@Audited
public class Category implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idCategory;
	private String				category;
	private Short				isDeleted;
	private Short				isChecked;
	private Integer				sortOrder;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_CATEGORY", unique = true, nullable = false, length = 11)
	public Integer getIdCategory()
	{
		return this.idCategory;
	}

	public void setIdCategory(Integer idCategory)
	{
		this.idCategory = idCategory;
	}

	@Column(name = "SORT_ORDER", nullable = false, length = 11)
	public Integer getSortOrder()
	{
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder)
	{
		this.sortOrder = sortOrder;
	}

	@Column(name = "CATEGORY", nullable = false, length = 128)
	public String getCategory()
	{
		return this.category;
	}

	public void setCategory(String category)
	{
		this.category = category;
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

	@Column(name = "IS_CHECKED", nullable = true, length = 1, columnDefinition = "smallint default 0")
	public Short getIsChecked()
	{
		return isChecked;
	}

	public void setIsChecked(Short isChecked)
	{
		this.isChecked = isChecked;
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

	@Column(name = "UPDATED_BY", nullable = true, length = 11)
	public Integer getUpdatedBy()
	{
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	@Column(name = "UPDATED_ON", nullable = true)
	public Timestamp getUpdatedOn()
	{
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn)
	{
		this.updatedOn = updatedOn;
	}
}