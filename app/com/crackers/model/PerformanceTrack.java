package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PerformanceTrack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"PERFORMANCE_TRACK\"")
public class PerformanceTrack implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	private Integer				idPerformanceTrack;
	private Integer				idUser;
	private String				className;
	private String				method;
	private Long				threadId;
	private Integer				lineNumber;
	private Timestamp			startTime;
	private Timestamp			endTime;

	@Id
	@Column(name = "ID_PERFORMANCE_TRACK", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = IDENTITY)
	public Integer getIdPerformanceTrack()
	{
		return this.idPerformanceTrack;
	}

	public void setIdPerformanceTrack(Integer idPerformanceTrack)
	{
		this.idPerformanceTrack = idPerformanceTrack;
	}

	@Column(name = "ID_USER", nullable = false)
	public Integer getIdUser()
	{
		return this.idUser;
	}

	public void setIdUser(Integer user)
	{
		this.idUser = user;
	}

	@Column(name = "CLASS_NAME", nullable = false, length = 128)
	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	@Column(name = "METHOD_NAME", nullable = false, length = 128)
	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	@Column(name = "THREAD_ID", nullable = false)
	public Long getThreadId()
	{
		return threadId;
	}

	public void setThreadId(Long threadId)
	{
		this.threadId = threadId;
	}

	@Column(name = "LINE_NUMBER", nullable = false)
	public Integer getLineNumber()
	{
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber)
	{
		this.lineNumber = lineNumber;
	}

	@Column(name = "START_TIME", nullable = false)
	public Timestamp getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Timestamp startTime)
	{
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", nullable = false)
	public Timestamp getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Timestamp endTime)
	{
		this.endTime = endTime;
	}
}