package com.crackers.model;

import java.sql.Timestamp;

import javax.persistence.Lob;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "Image")
public class Image implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    @GraphId
    @Property(name = "idImage")
    private Integer           idImage;
    @Property(name = "idUser")
    private User              user;
    private byte[]            imageArr;
    @Property(name = "isDeleted")
    private Short             isDeleted;
    @Property(name = "createdBy")
    private Integer           createdBy;
    @Property(name = "createdOn")
    private Timestamp         createdOn;
    @Property(name = "updatedBy")
    private Integer           updatedBy;
    @Property(name = "updatedOn")
    private Timestamp         updatedOn;

    public Integer getIdImage()
    {
        return this.idImage;
    }

    public void setIdImage(Integer idImage)
    {
        this.idImage = idImage;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Property(name = "image")
    @Lob
    public byte[] getImage()
    {
        return this.imageArr;
    }

    public void setImage(byte[] image)
    {
        this.imageArr = image;
    }

    public Short getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(Short isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedOn()
    {
        return this.createdOn;
    }

    public void setCreatedOn(Timestamp createdOn)
    {
        this.createdOn = createdOn;
    }

    public Integer getUpdatedBy()
    {
        return this.updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedOn()
    {
        return this.updatedOn;
    }

    public void setUpdatedOn(Timestamp ts)
    {
        this.updatedOn = ts;
    }
}