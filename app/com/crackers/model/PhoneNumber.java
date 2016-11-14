package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * PhoneNumber entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "PhoneNumber")
public class PhoneNumber implements java.io.Serializable
{

    private static final long serialVersionUID  = 1L;
    private Integer           idPhoneNumber;
    private User              user;
    private String            phoneNumberValue;
    private Integer           phoneNumberSource = 1;
    private Integer           idPhoneType;
    private Short             isDeleted         = 0;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;

    @GraphId
    @Property(name = "idPhoneNumber")
    public Integer getIdPhoneNumber()
    {
        return this.idPhoneNumber;
    }

    public void setIdPhoneNumber(Integer isdPhoneNumber)
    {
        this.idPhoneNumber = isdPhoneNumber;
    }

    @Property(name = "idUser")
    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Property(name = "phoneNumber")
    public String getPhoneNumberValue()
    {
        return this.phoneNumberValue;
    }

    public void setPhoneNumberValue(String phoneNumberValue)
    {
        this.phoneNumberValue = phoneNumberValue;
    }

    @Property(name = "idPhoneType")
    public Integer getIdPhoneType()
    {
        return this.idPhoneType;
    }

    public void setIdPhoneType(Integer idPhoneType)
    {
        this.idPhoneType = idPhoneType;
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

    @Property(name = "phoneNumberSource")
    public Integer getPhoneNumberSource()
    {
        return phoneNumberSource;
    }

    public void setPhoneNumberSource(Integer phoneNumberSource)
    {
        this.phoneNumberSource = phoneNumberSource;
    }
}