package com.crackers.model;

import java.sql.Timestamp;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.google.common.collect.Sets;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "User")
public class User implements java.io.Serializable
{

    private static final long   serialVersionUID = 1L;
    private Integer             idUser;
    private String              name;
    private String              userName;
    private Integer             idSource;
    private String              title;
    private String              bioData;
    private Integer             idUserState;
    private Short               isDeleted;
    private Integer             idImageColorCode;
    private Integer             createdBy;
    private Timestamp           createdOn;
    private Integer             updatedBy;
    private Timestamp           updatedOn;
    private Timestamp           lastUpdatedOn;
    @Relationship(type = "HAS_AN_EMAIL", direction = Relationship.OUTGOING)
    private Email               email;
    @Relationship(type = "HAS_MULTIPLE_PHONE_NUMBERS", direction = Relationship.OUTGOING)
    private Set<PhoneNumber>    phoneNumbers     = Sets.newHashSet();
    @Relationship(type = "HAS_MULTIPLE_ADDRESSES", direction = Relationship.OUTGOING)
    private Set<ContactDetails> addresses        = Sets.newHashSet();
    @Relationship(type = "HAS_AN_IMAGE", direction = Relationship.OUTGOING)
    private Image               image;
    @Relationship(type = "HAS_A_PASSWORD", direction = Relationship.OUTGOING)
    private UserCredential      password;
    @Relationship(type = "HAS_A_ROLE", direction = Relationship.OUTGOING)
    private UserRole            userRole;

    @GraphId
    @Property(name = "idUser")
    public Integer getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    @Property(name = "name")
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Property(name = "userName")
    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Property(name = "imageColorCode")
    public Integer getIdImageColorCode()
    {
        return idImageColorCode;
    }

    public void setIdImageColorCode(Integer idImageColorCode)
    {
        this.idImageColorCode = idImageColorCode;
    }

    @Property(name = "idUserSource")
    public Integer getIdSource()
    {
        return idSource;
    }

    public void setIdSource(Integer idSource)
    {
        this.idSource = idSource;
    }

    @Property(name = "title")
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Property(name = "bioData")
    public String getBioData()
    {
        return this.bioData;
    }

    public void setBioData(String bioData)
    {
        this.bioData = bioData;
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

    @Property(name = "idUserState")
    public Integer getIdUserState()
    {
        return idUserState;
    }

    public void setIdUserState(Integer idUserState)
    {
        this.idUserState = idUserState;
    }

    @Property(name = "lastUpdatedOn")
    public Timestamp getLastUpdatedOn()
    {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Timestamp lastUpdatedOn)
    {
        this.lastUpdatedOn = lastUpdatedOn;
    }
}