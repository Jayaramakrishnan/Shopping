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

import org.hibernate.envers.Audited;

/**
 * ContactDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"CONTACT_DETAILS\"")
@Audited
public class ContactDetails implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idContactDetails;
	private User				user;
	private Integer				streetSource;
	private String				street;
	private Integer				citySource;
	private String				city;
	private Integer				stateSource;
	private String				state;
	private Integer				pincodeSource;
	private String				pincode;
	private Integer				websiteSource;
	private String				website;
	private Short				isDeleted;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;

	// Constructors
	/** default constructor */
	public ContactDetails() {
	}

	/** full constructor */
	public ContactDetails(User user, String street, String city, String state, String pincode, String website, Short isDeleted, Integer createdBy, Timestamp createdOn, Integer updatedBy, Timestamp updatedOn) {
		this.user = user;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.website = website;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_CONTACT_DETAILS", unique = true, nullable = false, length = 11)
	public Integer getIdContactDetails()
	{
		return this.idContactDetails;
	}

	public void setIdContactDetails(Integer idContactDetails)
	{
		this.idContactDetails = idContactDetails;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Column(name = "STREET", nullable = true, length = 128)
	public String getStreet()
	{
		return this.street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	@Column(name = "CITY", nullable = true, length = 128)
	public String getCity()
	{
		return this.city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	@Column(name = "STATE", nullable = true, length = 128)
	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Column(name = "PINCODE", nullable = true, length = 11)
	public String getPincode()
	{
		return this.pincode;
	}

	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}

	@Column(name = "WEBSITE", nullable = true, length = 128)
	public String getWebsite()
	{
		return this.website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
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

	@Column(name = "STREET_SOURCE", nullable = true, length = 11)
	public Integer getStreetSource()
	{
		return streetSource;
	}

	public void setStreetSource(Integer streetSource)
	{
		this.streetSource = streetSource;
	}

	@Column(name = "CITY_SOURCE", nullable = true, length = 11)
	public Integer getCitySource()
	{
		return citySource;
	}

	public void setCitySource(Integer citySource)
	{
		this.citySource = citySource;
	}

	@Column(name = "STATE_SOURCE", nullable = true, length = 11)
	public Integer getStateSource()
	{
		return stateSource;
	}

	public void setStateSource(Integer stateSource)
	{
		this.stateSource = stateSource;
	}

	@Column(name = "PINCODE_SOURCE", nullable = true, length = 11)
	public Integer getPincodeSource()
	{
		return pincodeSource;
	}

	public void setPincodeSource(Integer pincodeSource)
	{
		this.pincodeSource = pincodeSource;
	}

	@Column(name = "WEBSITE_SOURCE", nullable = true, length = 11)
	public Integer getWebsiteSource()
	{
		return websiteSource;
	}

	public void setWebsiteSource(Integer websiteSource)
	{
		this.websiteSource = websiteSource;
	}
}