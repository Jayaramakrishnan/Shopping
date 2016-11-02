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

/**
 * PhoneNumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"PHONE_NUMBER\"")
public class PhoneNumber implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idPhoneNumber;
	private User				user;
	private String				phoneNumber;
	private Integer				phoneNumberSource	= 1;
	private Integer				idPhoneType;
	private Short				isDeleted			= 0;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;

	// Constructors
	/** default constructor */
	public PhoneNumber() {
	}

	/** minimal constructor */
	public PhoneNumber(Integer isdPhoneNumber, User user, String phoneNumber, Integer idPhoneType, Short isDeleted, Integer createdBy, Timestamp createdOn) {
		this.idPhoneNumber = isdPhoneNumber;
		this.user = user;
		this.phoneNumber = phoneNumber;
		this.idPhoneType = idPhoneType;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	/** full constructor */
	public PhoneNumber(Integer isdPhoneNumber, User user, String phoneNumber, Integer idPhoneType, Short isDeleted, Integer createdBy, Timestamp createdOn, Integer updatedBy, Timestamp updatedOn) {
		this.idPhoneNumber = isdPhoneNumber;
		this.user = user;
		this.phoneNumber = phoneNumber;
		this.idPhoneType = idPhoneType;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_PHONE_NUMBER", unique = true, nullable = false, length = 11)
	public Integer getIdPhoneNumber()
	{
		return this.idPhoneNumber;
	}

	public void setIdPhoneNumber(Integer isdPhoneNumber)
	{
		this.idPhoneNumber = isdPhoneNumber;
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

	@Column(name = "PHONE_NUMBER", nullable = false, length = 50)
	public String getPhoneNumber()
	{
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "ID_PHONE_TYPE", nullable = true, length = 6)
	public Integer getIdPhoneType()
	{
		return this.idPhoneType;
	}

	public void setIdPhoneType(Integer idPhoneType)
	{
		this.idPhoneType = idPhoneType;
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

	@Column(name = "PHONE_NUMBER_SOURCE", nullable = false, length = 11)
	public Integer getPhoneNumberSource()
	{
		return phoneNumberSource;
	}

	public void setPhoneNumberSource(Integer phoneNumberSource)
	{
		this.phoneNumberSource = phoneNumberSource;
	}
}