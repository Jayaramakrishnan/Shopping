package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * ImageColorCode entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "ImageColorCode")
public class ImageColorCode implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    // Fields
    private Integer           idImageColorCode;
    private String            imageColorCodeValue;
    private Short             isDeleted;
    private Integer           createdBy;
    private Timestamp         createdOn;

    @GraphId
    @Property(name = "idImageColorCode")
    public Integer getIdImageColorCode()
    {
        return this.idImageColorCode;
    }

    public void setIdImageColorCode(Integer idImageColorCode)
    {
        this.idImageColorCode = idImageColorCode;
    }

    @Property(name = "imageColorCode")
    public String getImageColorCodeValue()
    {
        return this.imageColorCodeValue;
    }

    public void setImageColorCodeValue(String imageColorCode)
    {
        this.imageColorCodeValue = imageColorCode;
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
}