package com.crackers.vo;

public class UserSessionSettings
{

    private String  resourceId;
    private String  genericId;
    private Integer entityTypeId;
    private String  redirectUrl;
    private String  redirectIdActivity;

    public String getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(String resourceId)
    {
        this.resourceId = resourceId;
    }

    public String getGenericId()
    {
        return genericId;
    }

    public void setGenericId(String genericId)
    {
        this.genericId = genericId;
    }

    public Integer getEntityTypeId()
    {
        return entityTypeId;
    }

    public void setEntityTypeId(Integer entityTypeId)
    {
        this.entityTypeId = entityTypeId;
    }

    public String getRedirectUrl()
    {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectIdActivity()
    {
        return redirectIdActivity;
    }

    public void setRedirectIdActivity(String redirectIdActivity)
    {
        this.redirectIdActivity = redirectIdActivity;
    }
}