package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LoginTrack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"LOGIN_TRACK\"")
public class LoginTrack implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idLoginTrack;
	private User				user;
	private String				email;
	private String				userDevice;
	private String				userAgent;
	private String				clientIp;
	private Timestamp			loggedOnTime;
	private Timestamp			loggedOut;
	private String				sessionToken;
	private Short				isSuccess;
	private Short				isSessionExpired;
	private Integer				createdBy;
	private Timestamp			createdOn;

	@Id
	@Column(name = "ID_LOGIN_TRACK", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = IDENTITY)
	public Integer getIdLoginTrack()
	{
		return this.idLoginTrack;
	}

	public void setIdLoginTrack(Integer idLoginTrack)
	{
		this.idLoginTrack = idLoginTrack;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USER", nullable = true)
	public User getUser()
	{
		return this.user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Column(name = "USER_DEVICE", nullable = false, length = 128)
	public String getUserDevice()
	{
		return userDevice;
	}

	public void setUserDevice(String userDevice)
	{
		this.userDevice = userDevice;
	}

	@Column(name = "EMAIL", nullable = false, length = 256)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "USER_AGENT", nullable = false, length = 128)
	public String getUserAgent()
	{
		return this.userAgent;
	}

	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	@Column(name = "CLIENT_IP", nullable = false, length = 128)
	public String getClientIp()
	{
		return this.clientIp;
	}

	public void setClientIp(String clientIp)
	{
		this.clientIp = clientIp;
	}

	@Column(name = "LOGGED_ON_TIME", nullable = true)
	public Timestamp getLoggedOnTime()
	{
		return loggedOnTime;
	}

	public void setLoggedOnTime(Timestamp loggedOnTime)
	{
		this.loggedOnTime = loggedOnTime;
	}

	@Column(name = "LOGGED_OUT", nullable = true)
	public Timestamp getLoggedOut()
	{
		return this.loggedOut;
	}

	public void setLoggedOut(Timestamp loggedOut)
	{
		this.loggedOut = loggedOut;
	}

	@Column(name = "SESSION_TOKEN", nullable = false, length = 128)
	public String getSessionToken()
	{
		return this.sessionToken;
	}

	public void setSessionToken(String sessionToken)
	{
		this.sessionToken = sessionToken;
	}

	@Column(name = "IS_SUCCESS", nullable = true)
	public Short getIsSuccess()
	{
		return isSuccess;
	}

	public void setIsSuccess(Short isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	@Column(name = "IS_SESSION_EXPIRED", nullable = true)
	public Short getIsSessionExpired()
	{
		return isSessionExpired;
	}

	public void setIsSessionExpired(Short isSessionExpired)
	{
		this.isSessionExpired = isSessionExpired;
	}

	@Column(name = "CREATED_BY", nullable = true, length = 11)
	public Integer getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_ON", nullable = false)
	public Timestamp getCreatedOn()
	{
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}
}