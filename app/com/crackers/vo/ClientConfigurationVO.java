package com.crackers.vo;

public class ClientConfigurationVO
{

    private String  version;
    private String  restBaseUrl;
    private String  sessionTimeout;
    private String  year;
    private String  applicationNameInLogin;
    private Short   isApplicationLogoAvailable;
    private String  applicationNameInheader;
    private String  applicationTitleName;
    private String  dateFormat;
    private Integer autoSaveTimeout;
    private String  isTLSOn;
    private Integer isSendEmailOn;

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getRestBaseUrl()
    {
        return restBaseUrl;
    }

    public void setRestBaseUrl(String restBaseUrl)
    {
        this.restBaseUrl = restBaseUrl;
    }

    public String getSessionTimeout()
    {
        return sessionTimeout;
    }

    public void setSessionTimeout(String sessionTimeout)
    {
        this.sessionTimeout = sessionTimeout;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getApplicationNameInLogin()
    {
        return applicationNameInLogin;
    }

    public void setApplicationNameInLogin(String applicationNameInLogin)
    {
        this.applicationNameInLogin = applicationNameInLogin;
    }

    public Short getIsApplicationLogoAvailable()
    {
        return isApplicationLogoAvailable;
    }

    public void setIsApplicationLogoAvailable(Short isApplicationLogoAvailable)
    {
        this.isApplicationLogoAvailable = isApplicationLogoAvailable;
    }

    public String getApplicationNameInheader()
    {
        return applicationNameInheader;
    }

    public void setApplicationNameInheader(String applicationNameInheader)
    {
        this.applicationNameInheader = applicationNameInheader;
    }

    public String getApplicationTitleName()
    {
        return applicationTitleName;
    }

    public void setApplicationTitleName(String applicationTitleName)
    {
        this.applicationTitleName = applicationTitleName;
    }

    public String getDateFormat()
    {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat)
    {
        this.dateFormat = dateFormat;
    }

    public Integer getAutoSaveTimeout()
    {
        return autoSaveTimeout;
    }

    public void setAutoSaveTimeout(Integer autoSaveTimeout)
    {
        this.autoSaveTimeout = autoSaveTimeout;
    }

    public String getIsTLSOn()
    {
        return isTLSOn;
    }

    public void setIsTLSOn(String isTLSOn)
    {
        this.isTLSOn = isTLSOn;
    }

    public Integer getIsSendEmailOn()
    {
        return isSendEmailOn;
    }

    public void setIsSendEmailOn(Integer isSendEmailOn)
    {
        this.isSendEmailOn = isSendEmailOn;
    }
}