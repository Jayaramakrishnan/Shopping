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
 * UserCredentialAud entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_CREDENTIAL_AUD")
public class UserCredentialAud implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer		rev;
	private Integer		idUserCredential;
	private Short		revtype;
	private Integer		idUser;
	private String		saltKey;
	private String		hashedKey;
	private Short		isDeleted;
	private Integer		createdBy;
	private Timestamp	createdOn;
	private Integer		updatedBy;
	private Timestamp	updatedOn;

	// Constructors
	/** default constructor */
	public UserCredentialAud() {
	}

	/** minimal constructor */
	public UserCredentialAud(Integer rev, Integer idUserCredential, Integer idUser, String saltKey, String hashedKey, Short isDeleted, Integer createdBy, Timestamp createdOn) {
		this.rev = rev;
		this.idUserCredential = idUserCredential;
		this.idUser = idUser;
		this.saltKey = saltKey;
		this.hashedKey = hashedKey;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	/** full constructor */
	public UserCredentialAud(Integer rev, Integer idUserCredential, Short revtype, Integer idUser, String saltKey, String hashedKey, Short isDeleted, Integer createdBy, Timestamp createdOn, Integer updatedBy, Timestamp updatedOn) {
		this.rev = rev;
		this.idUserCredential = idUserCredential;
		this.revtype = revtype;
		this.idUser = idUser;
		this.saltKey = saltKey;
		this.hashedKey = hashedKey;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	// Property accessors
	@Id
	@Column(name = "REV", unique = true, nullable = false)
	@GeneratedValue(strategy = IDENTITY)
	public Integer getRev()
	{
		return this.rev;
	}

	public void setRev(Integer rev)
	{
		this.rev = rev;
	}

	@Column(name = "ID_USER_CREDENTIAL", nullable = false)
	public Integer getIdUserCredential()
	{
		return this.idUserCredential;
	}

	public void setIdUserCredential(Integer idUserCredential)
	{
		this.idUserCredential = idUserCredential;
	}

	@Column(name = "REVTYPE")
	public Short getRevtype()
	{
		return this.revtype;
	}

	public void setRevtype(Short revtype)
	{
		this.revtype = revtype;
	}

	@Column(name = "ID_USER", nullable = false)
	public Integer getIdUser()
	{
		return this.idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	@Column(name = "SALT_KEY", nullable = false, length = 8)
	public String getSaltKey()
	{
		return this.saltKey;
	}

	public void setSaltKey(String saltKey)
	{
		this.saltKey = saltKey;
	}

	@Column(name = "HASHED_KEY", nullable = false, length = 256)
	public String getHashedKey()
	{
		return this.hashedKey;
	}

	public void setHashedKey(String hashedKey)
	{
		this.hashedKey = hashedKey;
	}

	@Column(name = "IS_DELETED", nullable = false)
	public Short getIsDeleted()
	{
		return this.isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	@Column(name = "CREATED_BY", nullable = false)
	public Integer getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_ON", nullable = false, length = 19)
	public Timestamp getCreatedOn()
	{
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}

	@Column(name = "UPDATED_BY")
	public Integer getUpdatedBy()
	{
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	@Column(name = "UPDATED_ON", length = 19)
	public Timestamp getUpdatedOn()
	{
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn)
	{
		this.updatedOn = updatedOn;
	}
}