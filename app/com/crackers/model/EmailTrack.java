package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * EmailTrack entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "EmailTrack")
public class EmailTrack implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    // Fields
    private Integer           idEmailTrack;
    private Integer           idGeneric;
    private Integer           idRecipient;
    private String            email;
    private Integer           idEmailTemplate;
    private Short             isMailSend;
    private Short             isDeleted;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;

    @GraphId
    @Property(name = "idEmailTrack")
    public Integer getIdEmailTrack()
    {
        return this.idEmailTrack;
    }

    public void setIdEmailTrack(Integer idEmailTrack)
    {
        this.idEmailTrack = idEmailTrack;
    }

    @Property(name = "idGeneric")
    public Integer getIdGeneric()
    {
        return this.idGeneric;
    }

    public void setIdGeneric(Integer idGeneric)
    {
        this.idGeneric = idGeneric;
    }

    @Property(name = "idRecipient")
    public Integer getIdRecipient()
    {
        return this.idRecipient;
    }

    public void setIdRecipient(Integer idRecipient)
    {
        this.idRecipient = idRecipient;
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

    @Property(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Property(name = "idEmailTemplate")
    public Integer getIdEmailTemplate()
    {
        return idEmailTemplate;
    }

    public void setIdEmailTemplate(Integer idEmailTemplate)
    {
        this.idEmailTemplate = idEmailTemplate;
    }

    @Property(name = "isMailSend")
    public Short getIsMailSend()
    {
        return isMailSend;
    }

    public void setIsMailSend(Short isMailSend)
    {
        this.isMailSend = isMailSend;
    }
}