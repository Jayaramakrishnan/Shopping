package com.crackers.dto;

public class ContactDetailsDto
{

    // Fields
    private Integer idContactDetails;
    private UserDto userDto;
    private Integer streetSource;
    private String  street;
    private Integer citySource;
    private String  city;
    private Integer stateSource;
    private String  state;
    private Integer pincodeSource;
    private String  pincode;
    private Integer websiteSource;
    private String  website;
    private Short   isDeleted;

    public Integer getIdContactDetails()
    {
        return idContactDetails;
    }

    public void setIdContactDetails(Integer idContactDetails)
    {
        this.idContactDetails = idContactDetails;
    }

    public UserDto getUserDto()
    {
        return userDto;
    }

    public void setUserDto(UserDto userDto)
    {
        this.userDto = userDto;
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

    public Integer getStreetSource()
    {
        return streetSource;
    }

    public void setStreetSource(Integer streetSource)
    {
        this.streetSource = streetSource;
    }

    public Integer getCitySource()
    {
        return citySource;
    }

    public void setCitySource(Integer citySource)
    {
        this.citySource = citySource;
    }

    public Integer getStateSource()
    {
        return stateSource;
    }

    public void setStateSource(Integer stateSource)
    {
        this.stateSource = stateSource;
    }

    public Integer getPincodeSource()
    {
        return pincodeSource;
    }

    public void setPincodeSource(Integer pincodeSource)
    {
        this.pincodeSource = pincodeSource;
    }

    public Integer getWebsiteSource()
    {
        return websiteSource;
    }

    public void setWebsiteSource(Integer websiteSource)
    {
        this.websiteSource = websiteSource;
    }
}