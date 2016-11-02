package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"USER\"")
@Audited
public class User implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	private Integer				idUser;
	private String				firstName;
	private String				lastName;
	private String				fullName;
	private String				userName;
	private Integer				idSource;
	private String				smaAccountName;
	private String				uniqueId;
	private String				title;
	private String				bioData;
	private Integer				idUserState;
	private Integer				firstNameSource;
	private Integer				lastNameSource;
	private Integer				bioDataSource;
	private Integer				uniqueIdSource;
	private Short				isDeleted;
	private Integer				idImageColorCode;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;
	private Timestamp			lastUpdatedOn;

	@Id
	@Column(name = "ID_USER", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = IDENTITY)
	public Integer getIdUser()
	{
		return this.idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	@Column(name = "FIRST_NAME", nullable = true, length = 128)
	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = true, length = 128)
	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Column(name = "FULL_NAME", nullable = false, length = 512)
	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	@Column(name = "USER_NAME", nullable = false, length = 128)
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name = "IMAGE_COLOR_CODE", nullable = true, length = 11)
	public Integer getIdImageColorCode()
	{
		return idImageColorCode;
	}

	public void setIdImageColorCode(Integer idImageColorCode)
	{
		this.idImageColorCode = idImageColorCode;
	}

	@Column(name = "ID_USER_SOURCE", nullable = false, length = 11)
	public Integer getIdSource()
	{
		return idSource;
	}

	public void setIdSource(Integer idSource)
	{
		this.idSource = idSource;
	}

	@Column(name = "SMA_ACCOUNT_NAME", length = 128, nullable = true)
	public String getSmaAccountName()
	{
		return this.smaAccountName;
	}

	public void setSmaAccountName(String smaAccountName)
	{
		this.smaAccountName = smaAccountName;
	}

	@Column(name = "UNIQUE_ID", length = 128, nullable = true)
	public String getUniqueId()
	{
		return this.uniqueId;
	}

	public void setUniqueId(String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	@Column(name = "UNIQUE_ID_SOURCE", nullable = true, length = 11)
	public Integer getUniqueIdSource()
	{
		return uniqueIdSource;
	}

	public void setUniqueIdSource(Integer uniqueIdSource)
	{
		this.uniqueIdSource = uniqueIdSource;
	}

	@Column(name = "TITLE", length = 128, nullable = true)
	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Column(name = "BIO_DATA", length = 4000, nullable = true)
	public String getBioData()
	{
		return this.bioData;
	}

	public void setBioData(String bioData)
	{
		this.bioData = bioData;
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

	@Column(name = "FIRST_NAME_SOURCE", nullable = true, length = 11)
	public Integer getFirstNameSource()
	{
		return firstNameSource;
	}

	public void setFirstNameSource(Integer firstNameSource)
	{
		this.firstNameSource = firstNameSource;
	}

	@Column(name = "LAST_NAME_SOURCE", nullable = true, length = 11)
	public Integer getLastNameSource()
	{
		return lastNameSource;
	}

	public void setLastNameSource(Integer lastNameSource)
	{
		this.lastNameSource = lastNameSource;
	}

	@Column(name = "BIO_DATA_SOURCE", nullable = true, length = 11)
	public Integer getBioDataSource()
	{
		return bioDataSource;
	}

	public void setBioDataSource(Integer bioDataSource)
	{
		this.bioDataSource = bioDataSource;
	}

	@Column(name = "ID_USER_STATE", updatable = true, nullable = false, length = 11, columnDefinition = "smallint default 1")
	public Integer getIdUserState()
	{
		return idUserState;
	}

	public void setIdUserState(Integer idUserState)
	{
		this.idUserState = idUserState;
	}

	@Column(name = "LAST_UPDATED_ON", nullable = true)
	public Timestamp getLastUpdatedOn()
	{
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn)
	{
		this.lastUpdatedOn = lastUpdatedOn;
	}
}