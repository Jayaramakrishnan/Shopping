package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * RoleFunctionalAccess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"ROLE_FUNCTIONAL_ACCESS\"")
@Audited
public class RoleFunctionalAccess implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	private Integer				idRoleFunctionalAccess;
	private FunctionalAccess	functionalAccess;
	private FunctionalResource	functionalResource;
	private Role				role;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;
	private Short				isDeleted;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_ROLE_FUNCTIONAL_ACCESS", unique = true, nullable = false, length = 10)
	public Integer getIdRoleFunctionalAccess()
	{
		return idRoleFunctionalAccess;
	}

	public void setIdRoleFunctionalAccess(Integer idRoleFunctionalAccess)
	{
		this.idRoleFunctionalAccess = idRoleFunctionalAccess;
	}

	@ManyToOne
	@JoinColumn(name = "ID_FUNCTIONAL_ACCESS")
	@NotAudited
	public FunctionalAccess getFunctionalAccess()
	{
		return functionalAccess;
	}

	public void setFunctionalAccess(FunctionalAccess functionalAccess)
	{
		this.functionalAccess = functionalAccess;
	}

	@ManyToOne
	@JoinColumn(name = "ID_FUNCTIONAL_RESOURCE")
	@NotAudited
	public FunctionalResource getFunctionalResource()
	{
		return functionalResource;
	}

	public void setFunctionalResource(FunctionalResource functionalResource)
	{
		this.functionalResource = functionalResource;
	}

	@ManyToOne
	@JoinColumn(name = "ID_ROLE")
	@NotAudited
	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 10)
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

	@Column(name = "UPDATED_BY", nullable = true, length = 10)
	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	@Column(name = "UPDATED_ON", nullable = true)
	public Timestamp getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn)
	{
		this.updatedOn = updatedOn;
	}

	@Column(name = "IS_DELETED", nullable = false, length = 11, columnDefinition = "smallint default 0")
	public Short getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}
}
