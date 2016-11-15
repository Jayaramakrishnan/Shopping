package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * UserSource entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "UserSource")
public class UserSource implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idSource;
    private String            source;
    private Integer           createdBy;
    private Timestamp         createdOn;

    @GraphId
    @Property(name = "idUserSource")
    public Integer getIdSource()
    {
        return this.idSource;
    }

    public void setIdSource(Integer idSource)
    {
        this.idSource = idSource;
    }

    @Property(name = "source")
    public String getSource()
    {
        return this.source;
    }

    public void setSource(String source)
    {
        this.source = source;
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