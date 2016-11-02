package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ApplicationConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"APPLICATION_CONFIG\"")
public class ApplicationConfig implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer		idApplicationConfig;
	private String		configKey;
	private String		configValue;
	private Integer		createdBy;
	private Timestamp	createdOn;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_APPLICATION_CONFIG", unique = true, nullable = false, length = 11)
	public Integer getIdApplicationConfig()
	{
		return this.idApplicationConfig;
	}

	public void setIdApplicationConfig(Integer idApplicationConfig)
	{
		this.idApplicationConfig = idApplicationConfig;
	}

	@Column(name = "CONFIG_KEY", nullable = false, length = 256)
	public String getConfigKey()
	{
		return this.configKey;
	}

	public void setConfigKey(String configKey)
	{
		this.configKey = configKey;
	}

	@Column(name = "CONFIG_VALUE", nullable = false, length = 128)
	public String getConfigValue()
	{
		return this.configValue;
	}

	public void setConfigValue(String configValue)
	{
		this.configValue = configValue;
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