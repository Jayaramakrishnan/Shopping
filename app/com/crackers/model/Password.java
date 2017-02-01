package com.crackers.model;

// default package
import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Password entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "Password")
public class Password implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	private Integer				idPassword;
	private Integer				idUser;
	private String				encryptText;
	private String				email;
	private String				saltKey;
	private Short				isDeleted;
	private Short				isExpired;
	private Integer				createdBy;
	private Timestamp			createdOn;

	@GraphId
	@Property(name = "idPassword")
	public Integer getIdPassword()
	{
		return this.idPassword;
	}

	public void setIdPassword(Integer idPassword)
	{
		this.idPassword = idPassword;
	}

	@Property(name = "idUser")
	public Integer getIdUser()
	{
		return idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	@Property(name = "encryptText")
	public String getEncryptText()
	{
		return this.encryptText;
	}

	public void setEncryptText(String encryptText)
	{
		this.encryptText = encryptText;
	}

	@Property(name = "email")
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Property(name = "saltKey")
	public String getSaltKey()
	{
		return this.saltKey;
	}

	public void setSaltKey(String saltKey)
	{
		this.saltKey = saltKey;
	}

	@Property(name = "isDeleted")
	public Short getIsDeleted()
	{
		return this.isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	@Property(name = "isExpired")
	public Short getIsExpired()
	{
		return isExpired;
	}

	public void setIsExpired(Short isExpired)
	{
		this.isExpired = isExpired;
	}

	@Property(name = "createdBy")
	public Integer getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	@Property(name = "createdOn")
	public Timestamp getCreatedOn()
	{
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}
}