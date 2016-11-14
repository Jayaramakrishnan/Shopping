package com.crackers.dto;

public class ImageColorCodeDto
{

    private Integer idImageColorCode;
    private String  imageColorCode;
    private Short   isDeleted;

    public Integer getIdImageColorCode()
    {
        return idImageColorCode;
    }

    public void setIdImageColorCode(Integer idImageColorCode)
    {
        this.idImageColorCode = idImageColorCode;
    }

    public String getImageColorCode()
    {
        return imageColorCode;
    }

    public void setImageColorCode(String imageColorCode)
    {
        this.imageColorCode = imageColorCode;
    }

    public Short getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted)
    {
        this.isDeleted = isDeleted;
    }
}