package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * KeywordTrack entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "KeywordTrack")
public class KeywordTrack implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idKeywordTrack;
    private String            form;
    private String            keyword;
    private Integer           idUser;
    private Integer           count;
    private String            noOfMatches;
    private String            exception;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;
    private String            url;

    @GraphId
    @Property(name = "idKeywordTrack")
    public Integer getIdKeywordTrack()
    {
        return this.idKeywordTrack;
    }

    public void setIdKeywordTrack(Integer idKeywordTrack)
    {
        this.idKeywordTrack = idKeywordTrack;
    }

    @Property(name = "keyword")
    public String getKeyword()
    {
        return this.keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    @Property(name = "exception")
    public String getException()
    {
        return exception;
    }

    public void setException(String exception)
    {
        this.exception = exception;
    }

    @Property(name = "idUser")
    public Integer getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    @Property(name = "noOfMatches")
    public String getNoOfMatches()
    {
        return noOfMatches;
    }

    public void setNoOfMatches(String noOfMatches)
    {
        this.noOfMatches = noOfMatches;
    }

    @Property(name = "form")
    public String getForm()
    {
        return form;
    }

    public void setForm(String form)
    {
        this.form = form;
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

    @Property(name = "updatedBy")
    public Integer getUpdatedBy()
    {
        return this.updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @Property(name = "updatedOn")
    public Timestamp getUpdatedOn()
    {
        return this.updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn)
    {
        this.updatedOn = updatedOn;
    }

    @Property(name = "count")
    public Integer getCount()
    {
        return this.count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    @Property(name = "url")
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}