package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * ContactDetails entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "ContactDetails")
public class ContactDetails implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idContactDetails;
    private User              user;
    private Integer           streetSource;
    private String            street;
    private Integer           citySource;
    private String            city;
    private Integer           stateSource;
    private String            state;
    private Integer           pincodeSource;
    private String            pincode;
    private Integer           websiteSource;
    private String            website;
    private Short             isDeleted;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;

    @GraphId
    @Property(name = "idContactDetails")
    public Integer getIdContactDetails()
    {
        return this.idContactDetails;
    }

    public void setIdContactDetails(Integer idContactDetails)
    {
        this.idContactDetails = idContactDetails;
    }

    @Property(name = "idUser")
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Property(name = "street")
    public String getStreet()
    {
        return this.street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    @Property(name = "city")
    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Property(name = "state")
    public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    @Property(name = "pincode")
    public String getPincode()
    {
        return this.pincode;
    }

    public void setPincode(String pincode)
    {
        this.pincode = pincode;
    }

    @Property(name = "website")
    public String getWebsite()
    {
        return this.website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
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

    @Property(name = "updatedBy")
    public Integer getUpdatedBy()
    {
        return this.updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @Property(name = "updatedOn")
    public Timestamp getUpdatedOn()
    {
        return this.updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn)
    {
        this.updatedOn = updatedOn;
    }

    @Property(name = "streetSource")
    public Integer getStreetSource()
    {
        return streetSource;
    }

    public void setStreetSource(Integer streetSource)
    {
        this.streetSource = streetSource;
    }

    @Property(name = "citySource")
    public Integer getCitySource()
    {
        return citySource;
    }

    public void setCitySource(Integer citySource)
    {
        this.citySource = citySource;
    }

    @Property(name = "stateSource")
    public Integer getStateSource()
    {
        return stateSource;
    }

    public void setStateSource(Integer stateSource)
    {
        this.stateSource = stateSource;
    }

    @Property(name = "pincodeSource")
    public Integer getPincodeSource()
    {
        return pincodeSource;
    }

    public void setPincodeSource(Integer pincodeSource)
    {
        this.pincodeSource = pincodeSource;
    }

    @Property(name = "websiteSource")
    public Integer getWebsiteSource()
    {
        return websiteSource;
    }

    public void setWebsiteSource(Integer websiteSource)
    {
        this.websiteSource = websiteSource;
    }
}