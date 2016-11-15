package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * ApplicationConfig entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "ApplicationConfig")
public class ApplicationConfig implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    // Fields
    private Integer           idApplicationConfig;
    private String            configKey;
    private String            configValue;
    private Integer           createdBy;
    private Timestamp         createdOn;

    @GraphId
    @Property(name = "idApplicationConfig")
    public Integer getIdApplicationConfig()
    {
        return this.idApplicationConfig;
    }

    public void setIdApplicationConfig(Integer idApplicationConfig)
    {
        this.idApplicationConfig = idApplicationConfig;
    }

    @Property(name = "configKey")
    public String getConfigKey()
    {
        return this.configKey;
    }

    public void setConfigKey(String configKey)
    {
        this.configKey = configKey;
    }

    @Property(name = "configValue")
    public String getConfigValue()
    {
        return this.configValue;
    }

    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
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