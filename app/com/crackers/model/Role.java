package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"ROLE\"")
public class Role implements java.io.Serializable
{

	private static final long			serialVersionUID		= 1L;
	// Fields
	private Integer						idRole;
	private String						role;
	private Short						isDeleted;
	private Integer						createdBy;
	private Timestamp					createdOn;
	private Integer						updatedBy;
	private Timestamp					updatedOn;
	private Set<RoleFunctionalAccess>	roleFunctionalAccess	= new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_ROLE", unique = true, nullable = false, length = 11)
	public Integer getIdRole()
	{
		return this.idRole;
	}

	public void setIdRole(Integer idRole)
	{
		this.idRole = idRole;
	}

	@Column(name = "ROLE", nullable = false, length = 128)
	public String getRole()
	{
		return this.role;
	}

	public void setRole(String role)
	{
		this.role = role;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	public Set<RoleFunctionalAccess> getRoleFunctionalAccess()
	{
		return roleFunctionalAccess;
	}

	public void setRoleFunctionalAccess(Set<RoleFunctionalAccess> roleFunctionalAccess)
	{
		this.roleFunctionalAccess = roleFunctionalAccess;
	}
}