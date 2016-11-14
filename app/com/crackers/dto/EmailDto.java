package com.crackers.dto;

public class EmailDto
{

    private Integer idEmail;
    private UserDto userDto;
    private String  email;
    private Short   isDeleted;
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

    public UserDto getUserDto()
    {
        return userDto;
    }

    public void setUserDto(UserDto userDto)
    {
        this.userDto = userDto;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Short getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted)
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