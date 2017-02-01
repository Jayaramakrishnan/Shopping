package com.crackers.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "UserCredential")
public class UserCredential implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	@GraphId
	@Property(name = "idUserCredential")
	private Long				idUserCredential;
	@Property(name = "idUser")
	private Integer				idUser;
	@Property(name = "saltKey")
	private String				saltKey;
	@Property(name = "hashedKey")
	private String				hashedKey;
	@Property(name = "isDeleted")
	private Short				isDeleted;
	@Property(name = "createdBy")
	private Integer				createdBy;
	@Property(name = "createdOn")
	private Timestamp			createdOn;
	@Property(name = "updatedBy")
	private Integer				updatedBy;
	@Property(name = "updatedOn")
	private Timestamp			updatedOn;

	public Short getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	public Integer getIdUser()
	{
		return idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
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
