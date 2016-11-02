package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * EmailTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"EMAIL_TEMPLATE\"")
public class EmailTemplate implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer		idEmailTemplate;
	private String		subject;
	private byte[]		bodyData;
	private String		body;
	private Short		isDeleted;
	private Integer		createdBy;
	private Timestamp	createdOn;
	private Integer		updatedBy;
	private Timestamp	updatedOn;

	// Constructors
	/** default constructor */
	public EmailTemplate() {
	}

	/** full constructor */
	public EmailTemplate(String subject, byte[]	bodyData , Short isDeleted, Integer createdBy, Timestamp createdOn, Integer updatedBy, Timestamp updatedOn) {
		this.subject = subject;
		this.bodyData = bodyData;
		if(bodyData != null)
		{
			this.body = new String(bodyData);
		}
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_EMAIL_TEMPLATE", unique = true, nullable = false, length = 11)
	public Integer getIdEmailTemplate()
	{
		return this.idEmailTemplate;
	}

	public void setIdEmailTemplate(Integer idEmailTemplate)
	{
		this.idEmailTemplate = idEmailTemplate;
	}

	@Column(name = "SUBJECT", length = 128)
	public String getSubject()
	{
		return this.subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	@Transient
	public String getBody()
	{
		return this.body;
	}

	public void setBody(String body)
	{
		this.body = body;
		if(body != null)
		{
			this.bodyData = body.getBytes();
		}
	}

	@Column(name = "BODY", length = 16777216)
	@Lob
	public byte[] getBodyData()
	{
		return bodyData;
	}

	public void setBodyData(byte[] bodyData)
	{
		this.bodyData = bodyData;
		if(bodyData != null)
		{
			this.body = new String(bodyData);
		}
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