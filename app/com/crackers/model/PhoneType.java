package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * PhoneType entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "PhoneType")
public class PhoneType implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idPhoneType      = 1;
    private String            phoneType;
    private Integer           createdBy;
    private Timestamp         createdOn;

    @GraphId
    @Property(name = "idPhoneType")
    public Integer getIdPhoneType()
    {
        return this.idPhoneType;
    }

    public void setIdPhoneType(Integer idPhoneType)
    {
        this.idPhoneType = idPhoneType;
    }

    @Property(name = "phoneType")
    public String getPhoneType()
    {
        return this.phoneType;
    }

    public void setPhoneType(String phoneType)
    {
        this.phoneType = phoneType;
    }

    @Property(name = "CREATED_BY")
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