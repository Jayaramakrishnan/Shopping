package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * EmailTemplate entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "EmailTemplate")
public class EmailTemplate implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    // Fields
    private Integer           idEmailTemplate;
    private String            subject;
    private String            body;
    private Short             isDeleted;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;

    @GraphId
    @Property(name = "idEmailTemplate")
    public Integer getIdEmailTemplate()
    {
        return this.idEmailTemplate;
    }

    public void setIdEmailTemplate(Integer idEmailTemplate)
    {
        this.idEmailTemplate = idEmailTemplate;
    }

    @Property(name = "subject")
    public String getSubject()
    {
        return this.subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @Property(name = "body")
    public String getBody()
    {
        return this.body;
    }

    public void setBody(String body)
    {
        this.body = body;
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
}