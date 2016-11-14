package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * UserSource entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "UserState")
public class UserState implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idUserState;
    private String            state;
    private Integer           createdBy;
    private Timestamp         createdOn;

    @GraphId
    @Property(name = "idUserState")
    public Integer getIdUserState()
    {
        return idUserState;
    }

    public void setIdUserState(Integer idUserState)
    {
        this.idUserState = idUserState;
    }

    @Property(name = "state")
    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
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