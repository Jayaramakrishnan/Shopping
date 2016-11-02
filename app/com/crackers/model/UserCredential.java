package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "\"USER_CREDENTIAL\"")
@Audited
public class UserCredential
{

	@Id
	@Column(name = "ID_USER_CREDENTIAL", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = IDENTITY)
	public Long			idUserCredential;
	@Column(name = "ID_USER", nullable = false, length = 11)
	public Long			idUser;
	@Column(name = "SALT_KEY", nullable = false, length = 8)
	public String		saltKey;
	@Column(name = "HASHED_KEY", nullable = false, length = 256)
	public String		hashedKey;
	@Column(name = "IS_DELETED", nullable = false, length = 6)
	private Short		isDeleted;
	@Column(name = "CREATED_BY", nullable = false, length = 11)
	private Integer		createdBy;
	@Column(name = "CREATED_ON", nullable = false)
	private Timestamp	createdOn;
	@Column(name = "UPDATED_BY", nullable = true, length = 11)
	private Integer		updatedBy;
	@Column(name = "UPDATED_ON", nullable = true)
	private Timestamp	updatedOn;

	public Short getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	public String getHashedKey()
	{
		return hashedKey;
	}

	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}

	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn)
	{
		this.updatedOn = updatedOn;
	}

	public void setHashedKey(String hashedKey)
	{
		this.hashedKey = hashedKey;
	}

	public Long getIdUser()
	{
		return idUser;
	}

	public void setIdUser(Long idUser)
	{
		this.idUser = idUser;
	}

	public String getSaltKey()
	{
		return saltKey;
	}

	public void setSaltKey(String saltKey)
	{
		this.saltKey = saltKey;
	}

	public Long getIdUserCredential()
	{
		return idUserCredential;
	}

	public void setIdUserCredential(Long idUserCredential)
	{
		this.idUserCredential = idUserCredential;
	}
}
