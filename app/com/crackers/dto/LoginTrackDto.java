package com.crackers.dto;

import java.sql.Timestamp;

public class LoginTrackDto
{

    private Integer   idLoginTrack;
    private String    userDevice;
    private String    userAgent;
    private String    email;
    private String    clientIp;
    private Timestamp loggedOnTime;
    private Timestamp loggedOut;
    private String    sessionToken;
    private Short     isSuccess;
    private Short     isSessionExpired;
    private Integer   createdBy;
    private Timestamp createdOn;

    public Short getIsSessionExpired()
    {
        return isSessionExpired;
    }

    public void setIsSessionExpired(Short isSessionExpired)
    {
        this.isSessionExpired = isSessionExpired;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Timestamp getLoggedOnTime()
    {
        return loggedOnTime;
    }

    public void setLoggedOnTime(Timestamp loggedOnTime)
    {
        this.loggedOnTime = loggedOnTime;
    }

    public Short getIsSuccess()
    {
        return isSuccess;
    }

    public void setIsSuccess(Short isSuccess)
    {
        this.isSuccess = isSuccess;
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

    public Integer getIdLoginTrack()
    {
        return idLoginTrack;
    }

    public void setIdLoginTrack(Integer idLoginTrack)
    {
        this.idLoginTrack = idLoginTrack;
    }

    public String getUserDevice()
    {
        return userDevice;
    }

    public void setUserDevice(String userDevice)
    {
        this.userDevice = userDevice;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    public Timestamp getLoggedOut()
    {
        return loggedOut;
    }

    public void setLoggedOut(Timestamp loggedOut)
    {
        this.loggedOut = loggedOut;
    }

    public String getSessionToken()
    {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken)
    {
        this.sessionToken = sessionToken;
    }
}