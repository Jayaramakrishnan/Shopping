package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Email entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "Email")
public class Email implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idEmail;
    private User              user;
    private String            emailValue;
    private Integer           emailSource      = 1;
    private Short             isPrimary        = 1;
    private Short             isDeleted;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;

    @GraphId
    @Property(name = "idEmail")
    public Integer getIdEmail()
    {
        return this.idEmail;
    }

    public void setIdEmail(Integer idEmail)
    {
        this.idEmail = idEmail;
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

    @Property(name = "email")
    public String getEmailValue()
    {
        return this.emailValue;
    }

    public void setEmailValue(String email)
    {
        this.emailValue = email;
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

    @Property(name = "isPrimary")
    public Short getIsPrimary()
    {
        return isPrimary;
    }

    public void setIsPrimary(Short isPrimary)
    {
        this.isPrimary = isPrimary;
    }

    @Property(name = "emailSource")
    public Integer getEmailSource()
    {
        return emailSource;
    }

    public void setEmailSource(Integer emailSource)
    {
        this.emailSource = emailSource;
    }
}