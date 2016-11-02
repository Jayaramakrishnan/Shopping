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
 * Password entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"PASSWORD\"")
public class Password implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idPassword;
	private String				encryptText;
	private String				email;
	private Integer				idUser;
	private String				saltKey;
	private Integer				idFlag;
	private Short				isDeleted;
	private Short				isExpired;
	private Integer				createdBy;
	private Timestamp			createdOn;

	// Constructors
	/** default constructor */
	public Password() {
	}

	/** minimal constructor */
	public Password(String encryptText, String email, Integer idUser, String saltKey, Integer idFlag) {
		this.encryptText = encryptText;
		this.email = email;
		this.idUser = idUser;
		this.saltKey = saltKey;
		this.idFlag = idFlag;
	}

	/** full constructor */
	public Password(String encryptText, String email, Integer idUser, Short isExpired, String saltKey, Integer idFlag, Short isDeleted, Integer createdBy, Timestamp createdOn) {
		this.encryptText = encryptText;
		this.email = email;
		this.idUser = idUser;
		this.saltKey = saltKey;
		this.idFlag = idFlag;
		this.isDeleted = isDeleted;
		this.isExpired = isExpired;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_PASSWORD", unique = true, nullable = false, length = 11)
	public Integer getIdPassword()
	{
		return this.idPassword;
	}

	public void setIdPassword(Integer idPassword)
	{
		this.idPassword = idPassword;
	}

	@Column(name = "ENCRYPT_TEXT", nullable = false, length = 256)
	public String getEncryptText()
	{
		return this.encryptText;
	}

	public void setEncryptText(String encryptText)
	{
		this.encryptText = encryptText;
	}

	@Column(name = "EMAIL", nullable = false, length = 256)
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "ID_USER", nullable = false, length = 11)
	public Integer getIdUser()
	{
		return this.idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	@Column(name = "SALT_KEY", nullable = false, length = 256)
	public String getSaltKey()
	{
		return this.saltKey;
	}

	public void setSaltKey(String saltKey)
	{
		this.saltKey = saltKey;
	}

	@Column(name = "ID_FLAG", nullable = false, length = 11)
	public Integer getIdFlag()
	{
		return this.idFlag;
	}

	public void setIdFlag(Integer idFlag)
	{
		this.idFlag = idFlag;
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

	@Column(name = "IS_EXPIRED", nullable = true, length = 6)
	public Short getIsExpired()
	{
		return isExpired;
	}

	public void setIsExpired(Short isExpired)
	{
		this.isExpired = isExpired;
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