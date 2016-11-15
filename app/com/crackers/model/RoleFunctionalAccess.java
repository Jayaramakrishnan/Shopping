package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * RoleFunctionalAccess entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "RoleFunctionalAccess")
public class RoleFunctionalAccess implements java.io.Serializable
{

    private static final long  serialVersionUID = 1L;
    private Integer            idRoleFunctionalAccess;
    private FunctionalAccess   functionalAccess;
    private FunctionalResource functionalResource;
    private Role               role;
    private Integer            createdBy;
    private Timestamp          createdOn;
    private Integer            updatedBy;
    private Timestamp          updatedOn;
    private Short              isDeleted;

    @GraphId
    @Property(name = "idRoleFunctionalAccess")
    public Integer getIdRoleFunctionalAccess()
    {
        return idRoleFunctionalAccess;
    }

    public void setIdRoleFunctionalAccess(Integer idRoleFunctionalAccess)
    {
        this.idRoleFunctionalAccess = idRoleFunctionalAccess;
    }

    @Property(name = "idFunctionalAccess")
    public FunctionalAccess getFunctionalAccess()
    {
        return functionalAccess;
    }

    public void setFunctionalAccess(FunctionalAccess functionalAccess)
    {
        this.functionalAccess = functionalAccess;
    }

    @Property(name = "idFunctionalResource")
    public FunctionalResource getFunctionalResource()
    {
        return functionalResource;
    }

    public void setFunctionalResource(FunctionalResource functionalResource)
    {
        this.functionalResource = functionalResource;
    }

    @Property(name = "idRole")
    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    @Property(name = "createdBy")
    public Integer getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    @Property(name = "createdOn")
    public Timestamp getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn)
    {
        this.createdOn = createdOn;
    }

    @Property(name = "updatedBy")
    public Integer getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @Property(name = "updatedOn")
    public Timestamp getUpdatedOn()
    {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn)
    {
        this.updatedOn = updatedOn;
    }

    @Property(name = "isDeleted")
    public Short getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted)
    {
        this.isDeleted = isDeleted;
    }
}