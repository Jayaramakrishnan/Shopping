package com.crackers.vo;

public class PhoneVO
{

    private Integer idPhoneNumber;
    private String  phoneNumber;
    private short   isDeleted = 0;
    private Integer phoneNumberSource;
    private Integer idPhoneType;
    private String  phoneType;

    public Integer getIdPhoneNumber()
    {
        return idPhoneNumber;
    }

    public void setIdPhoneNumber(Integer idPhoneNumber)
    {
        this.idPhoneNumber = idPhoneNumber;
    }

    public short getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(short isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getPhoneNumberSource()
    {
        return phoneNumberSource;
    }

    public void setPhoneNumberSource(Integer phoneNumberSource)
    {
        this.phoneNumberSource = phoneNumberSource;
    }

    public Integer getIdPhoneType()
    {
        return idPhoneType;
    }

    public void setIdPhoneType(Integer idPhoneType)
    {
        this.idPhoneType = idPhoneType;
    }

    public String getPhoneType()
    {
        return phoneType;
    }

    public void setPhoneType(String phoneType)
    {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}