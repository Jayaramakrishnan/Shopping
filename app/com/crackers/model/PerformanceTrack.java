package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * PerformanceTrack entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "PerformanceTrack")
public class PerformanceTrack implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idPerformanceTrack;
    private Integer           idUser;
    private String            className;
    private String            method;
    private Long              threadId;
    private Integer           lineNumber;
    private Timestamp         startTime;
    private Timestamp         endTime;

    @GraphId
    @Property(name = "idPerformanceTrack")
    public Integer getIdPerformanceTrack()
    {
        return this.idPerformanceTrack;
    }

    public void setIdPerformanceTrack(Integer idPerformanceTrack)
    {
        this.idPerformanceTrack = idPerformanceTrack;
    }

    @Property(name = "idUser")
    public Integer getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser(Integer user)
    {
        this.idUser = user;
    }

    @Property(name = "className")
    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    @Property(name = "methodName")
    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    @Property(name = "threadId")
    public Long getThreadId()
    {
        return threadId;
    }

    public void setThreadId(Long threadId)
    {
        this.threadId = threadId;
    }

    @Property(name = "lineNumber")
    public Integer getLineNumber()
    {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    @Property(name = "startTime")
    public Timestamp getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Timestamp startTime)
    {
        this.startTime = startTime;
    }

    @Property(name = "endTime")
    public Timestamp getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Timestamp endTime)
    {
        this.endTime = endTime;
    }
}