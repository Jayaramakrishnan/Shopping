package com.crackers.dto;

public class UserStateDto
{

    private Integer   idUserState;
    private String    state;
    private Integer   createdBy;
    private Long createdOn;

    public Integer getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    public Long getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn)
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