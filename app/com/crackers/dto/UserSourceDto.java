package com.crackers.dto;

import java.sql.Timestamp;

public class UserSourceDto
{

    private Integer   idSource;
    private String    source;
    private Integer   createdBy;
    private Timestamp createdOn;

    public Integer getIdSource()
    {
        return idSource;
    }

    public void setIdSource(Integer idSource)
    {
        this.idSource = idSource;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
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
}