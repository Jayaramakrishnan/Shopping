package com.crackers.model;

import java.sql.Timestamp;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * LoginTrack entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "LoginTrack")
public class LoginTrack implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer           idLoginTrack;
    private Integer           idUser;
    private String            email;
    private String            userDevice;
    private String            userAgent;
    private String            clientIp;
    private Timestamp         loggedOnTime;
    private Timestamp         loggedOut;
    private String            sessionToken;
    private Short             isSuccess;
    private Short             isSessionExpired;
    private Integer           createdBy;
    private Timestamp         createdOn;

    @GraphId
    @Property(name = "idLoginTrack")
    public Integer getIdLoginTrack()
    {
        return this.idLoginTrack;
    }

    public void setIdLoginTrack(Integer idLoginTrack)
    {
        this.idLoginTrack = idLoginTrack;
    }

    @ManyToOne
    @JoinColumn(name = "idUser")
    public Integer getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    @Property(name = "userDevice")
    public String getUserDevice()
    {
        return userDevice;
    }

    public void setUserDevice(String userDevice)
    {
        this.userDevice = userDevice;
    }

    @Property(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Property(name = "userAgent")
    public String getUserAgent()
    {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    @Property(name = "clientIp")
    public String getClientIp()
    {
        return this.clientIp;
    }

    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    @Property(name = "loggedOnTime")
    public Timestamp getLoggedOnTime()
    {
        return loggedOnTime;
    }

    public void setLoggedOnTime(Timestamp loggedOnTime)
    {
        this.loggedOnTime = loggedOnTime;
    }

    @Property(name = "loggedOut")
    public Timestamp getLoggedOut()
    {
        return this.loggedOut;
    }

    public void setLoggedOut(Timestamp loggedOut)
    {
        this.loggedOut = loggedOut;
    }

    @Property(name = "sessionToken")
    public String getSessionToken()
    {
        return this.sessionToken;
    }

    public void setSessionToken(String sessionToken)
    {
        this.sessionToken = sessionToken;
    }

    @Property(name = "isSuccess")
    public Short getIsSuccess()
    {
        return isSuccess;
    }

    public void setIsSuccess(Short isSuccess)
    {
        this.isSuccess = isSuccess;
    }

    @Property(name = "isSessionExpired")
    public Short getIsSessionExpired()
    {
        return isSessionExpired;
    }

    public void setIsSessionExpired(Short isSessionExpired)
    {
        this.isSessionExpired = isSessionExpired;
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