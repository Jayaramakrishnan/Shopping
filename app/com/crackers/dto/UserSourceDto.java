package com.crackers.dto;

public class UserSourceDto
{

    private Integer   idSource;
    private String    source;
    private Integer   createdBy;
    private Long createdOn;

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

    public Long getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn)
    {
        this.createdOn = createdOn;
    }
}