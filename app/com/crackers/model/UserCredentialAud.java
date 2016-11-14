package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * UserCredentialAud entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "UserCredentialAud")
public class UserCredentialAud implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           rev;
    private Integer           idUserCredential;
    private Short             revtype;
    private Integer           idUser;
    private String            saltKey;
    private String            hashedKey;
    private Short             isDeleted;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;

    @GraphId
    @Property(name = "rev")
    public Integer getRev()
    {
        return this.rev;
    }

    public void setRev(Integer rev)
    {
        this.rev = rev;
    }

    @Property(name = "idUserCredential")
    public Integer getIdUserCredential()
    {
        return this.idUserCredential;
    }

    public void setIdUserCredential(Integer idUserCredential)
    {
        this.idUserCredential = idUserCredential;
    }

    @Property(name = "revtype")
    public Short getRevtype()
    {
        return this.revtype;
    }

    public void setRevtype(Short revtype)
    {
        this.revtype = revtype;
    }

    @Property(name = "idUser")
    public Integer getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    @Property(name = "saltKey")
    public String getSaltKey()
    {
        return this.saltKey;
    }

    public void setSaltKey(String saltKey)
    {
        this.saltKey = saltKey;
    }

    @Property(name = "hashedKey")
    public String getHashedKey()
    {
        return this.hashedKey;
    }

    public void setHashedKey(String hashedKey)
    {
        this.hashedKey = hashedKey;
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