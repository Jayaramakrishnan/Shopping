package com.crackers.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * Email entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"EMAIL\"")
@Audited
public class Email implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idEmail;
	private User				user;
	private String				email;
	private Integer				emailSource			= 1;
	private Short				isPrimary			= 1;
	private Short				isDeleted;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;

	// Constructors
	/** default constructor */
	public Email() {
	}

	/** minimal constructor */
	public Email(Integer idEmail, User user, String email, Short isDeleted, Integer createdBy, Timestamp createdOn) {
		this.idEmail = idEmail;
		this.user = user;
		this.email = email;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	/** full constructor */
	public Email(Integer idEmail, User user, String email, Short isDeleted, Integer createdBy, Timestamp createdOn, Integer updatedBy, Timestamp updatedOn) {
		this.idEmail = idEmail;
		this.user = user;
		this.email = email;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMAIL", unique = true, nullable = false, length = 11)
	public Integer getIdEmail()
	{
		return this.idEmail;
	}

	public void setIdEmail(Integer idEmail)
	{
		this.idEmail = idEmail;
	}

	@JoinColumn(name = "ID_USER")
	@ManyToOne
	public User getUser()
	{
		return this.user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Column(name = "EMAIL", length = 256)
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
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

	@Column(name = "IS_PRIMARY", nullable = false, length = 6, columnDefinition = "smallint default 1")
	public Short getIsPrimary()
	{
		return isPrimary;
	}

	public void setIsPrimary(Short isPrimary)
	{
		this.isPrimary = isPrimary;
	}

	@Column(name = "EMAIL_SOURCE", nullable = false, length = 11)
	public Integer getEmailSource()
	{
		return emailSource;
	}

	public void setEmailSource(Integer emailSource)
	{
		this.emailSource = emailSource;
	}
}