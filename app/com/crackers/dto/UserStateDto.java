package com.crackers.dto;

import java.sql.Timestamp;

/**
 * UserSource entity. @author MyEclipse Persistence Tools
 */
public class UserStateDto
{

    // Fields
    private Integer   idUserState;
    private String    state;
    private Integer   createdBy;
    private Timestamp createdOn;

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

    public Integer getIdUserState()
    {
        return idUserState;
    }

    public void setIdUserState(Integer idUserState)
    {
        this.idUserState = idUserState;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}