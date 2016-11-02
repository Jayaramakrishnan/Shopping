package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EmailTrack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"EMAIL_TRACK\"")
public class EmailTrack implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idEmailTrack;
	private Integer				idGeneric;
	private Integer				idRecipient;
	private String				email;
	private Integer				idEmailTemplate;
	private Short				isMailSend;
	private Short				isDeleted;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_EMAIL_TRACK", unique = true, nullable = false, length = 11)
	public Integer getIdEmailTrack()
	{
		return this.idEmailTrack;
	}

	public void setIdEmailTrack(Integer idEmailTrack)
	{
		this.idEmailTrack = idEmailTrack;
	}

	@Column(name = "ID_GENERIC", length = 11)
	public Integer getIdGeneric()
	{
		return this.idGeneric;
	}

	public void setIdGeneric(Integer idGeneric)
	{
		this.idGeneric = idGeneric;
	}

	@Column(name = "ID_RECIPIENT", length = 11)
	public Integer getIdRecipient()
	{
		return this.idRecipient;
	}

	public void setIdRecipient(Integer idRecipient)
	{
		this.idRecipient = idRecipient;
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

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "ID_EMAIL_TEMPLATE", nullable = false, length = 11)
	public Integer getIdEmailTemplate()
	{
		return idEmailTemplate;
	}

	public void setIdEmailTemplate(Integer idEmailTemplate)
	{
		this.idEmailTemplate = idEmailTemplate;
	}

	@Column(name = "IS_MAIL_SEND", nullable = false, length = 6)
	public Short getIsMailSend()
	{
		return isMailSend;
	}

	public void setIsMailSend(Short isMailSend)
	{
		this.isMailSend = isMailSend;
	}
}