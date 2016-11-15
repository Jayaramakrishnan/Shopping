/**
 * 
 * @author natarra
 *
 */
package com.crackers.vo;

public class UserVO
{

    private String  idUser;
    private String  userName;
    private Integer idRole;
    private String  role;
    private String  imageColorCode;

    public String getIdUser()
    {
        return idUser;
    }

    public void setIdUser(String idUser)
    {
        this.idUser = idUser;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Integer getIdRole()
    {
        return idRole;
    }

    public void setIdRole(Integer idRole)
    {
        this.idRole = idRole;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getImageColorCode()
    {
        return imageColorCode;
    }

    public void setImageColorCode(String imageColorCode)
    {
        this.imageColorCode = imageColorCode;
    }
}