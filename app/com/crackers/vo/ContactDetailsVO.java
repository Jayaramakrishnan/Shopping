package com.crackers.vo;

public class ContactDetailsVO
{

	private Integer	idContactDetails;
	private String	street;
	private String	city;
	private String	state;
	private String	pincode;
	private String	website;
	private Short	isDeleted;

	public Integer getIdContactDetails()
	{
		return idContactDetails;
	}

	public void setIdContactDetails(Integer idContactDetails)
	{
		this.idContactDetails = idContactDetails;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getPincode()
	{
		return pincode;
	}

	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}

	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public Short getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
	}
}
