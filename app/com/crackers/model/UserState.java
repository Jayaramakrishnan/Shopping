package com.crackers.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserSource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"USER_STATE\"")
public class UserState implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer		idUserState;
	private String		state;
	private Integer		createdBy;
	private Timestamp	createdOn;

	// Constructors
	/** default constructor */
	public UserState() {
	}

	/** full constructor */
	public UserState(Integer idUserState, String state, Integer createdBy, Timestamp createdOn) {
		super();
		this.idUserState = idUserState;
		this.state = state;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	// Property accessors
	@Id
	@Column(name = "ID_USER_STATE", unique = true, nullable = false, length = 11)
	public Integer getIdUserState()
	{
		return idUserState;
	}

	public void setIdUserState(Integer idUserState)
	{
		this.idUserState = idUserState;
	}

	@Column(name = "STATE", nullable = false, length = 255)
	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
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