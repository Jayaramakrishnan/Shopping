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
 * UserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"USER_ROLE\"")
@Audited
public class UserRole implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idUserRole;
	private User				user;
	private Role				role;
	private Short				isDeleted;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_USER_ROLE", unique = true, nullable = false, length = 11)
	public Integer getIdUserRole()
	{
		return this.idUserRole;
	}

	public void setIdUserRole(Integer idUserRole)
	{
		this.idUserRole = idUserRole;
	}

	@JoinColumn(name = "ID_USER")
	@ManyToOne
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@JoinColumn(name = "ID_ROLE")
	@ManyToOne
	@NotAudited
	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
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
}