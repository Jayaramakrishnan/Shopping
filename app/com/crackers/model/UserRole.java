package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "UserRole")
public class UserRole implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    @GraphId
    @Property(name = "idUserRole")
    private Integer           idUserRole;
    @Property(name = "idUser")
    private Integer           idUser;
    @Property(name = "idRole")
    private Role              role;
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

    public Integer getIdUserRole()
    {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole)
    {
        this.idUserRole = idUserRole;
    }

    public Integer getIdUser()
    {
        return idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public Short getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn)
    {
        this.createdOn = createdOn;
    }

    public Integer getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedOn()
    {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn)
    {
        this.updatedOn = updatedOn;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
}