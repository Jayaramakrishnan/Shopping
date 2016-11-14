package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * FunctionalResource entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "FunctionalResource")
public class FunctionalResource implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    // Fields
    private Integer           idFunctionalResource;
    private String            functions;
    private Integer           createdBy;
    private Timestamp         createdOn;

    @GraphId
    @Property(name = "idFunctionalResource")
    public Integer getIdFunctionalResource()
    {
        return idFunctionalResource;
    }

    public void setIdFunctionalResource(Integer idFunctionalResource)
    {
        this.idFunctionalResource = idFunctionalResource;
    }

    @Property(name = "functions")
    public String getFunctions()
    {
        return functions;
    }

    public void setFunctions(String functions)
    {
        this.functions = functions;
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
}