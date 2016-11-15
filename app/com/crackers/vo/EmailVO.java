package com.crackers.vo;

public class EmailVO
{

    private Integer idEmail;
    private String  email;
    private short   isDeleted = 0;
    private Integer emailSource;
    private Short   isPrimary;

    public Integer getIdEmail()
    {
        return idEmail;
    }

    public void setIdEmail(Integer idEmail)
    {
        this.idEmail = idEmail;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public short getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(short isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getEmailSource()
    {
        return emailSource;
    }

    public void setEmailSource(Integer emailSource)
    {
        this.emailSource = emailSource;
    }

    public Short getIsPrimary()
    {
        return isPrimary;
    }

    public void setIsPrimary(Short isPrimary)
    {
        this.isPrimary = isPrimary;
    }
}