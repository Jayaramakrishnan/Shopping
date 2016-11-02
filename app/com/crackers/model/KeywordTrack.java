package com.crackers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * KeywordTrack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "\"KEYWORD_TRACK\"")
public class KeywordTrack implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	// Fields
	private Integer				idKeywordTrack;
	private String				form;
	//private byte[]				keywordBytes;
	private String				keyword;
	private Integer				idUser;
	private Integer				count;
	private String				noOfMatches;
	private String				exception;
	private Integer				createdBy;
	private Timestamp			createdOn;
	private Integer				updatedBy;
	private Timestamp			updatedOn;
	private String 				url;

	// Constructors
	/** default constructor */
	public KeywordTrack() {
	}

	public KeywordTrack(Integer idKeywordTrack, String form, String keyword, Integer idUser, Integer createdBy, Timestamp createdOn, Integer updatedBy, Timestamp updatedOn,Integer count,String url) {
		super();
		this.idKeywordTrack = idKeywordTrack;
		this.form = form;
		this.keyword = keyword;
		this.idUser = idUser;
		this.count = count;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_KEYWORD_TRACK", unique = true, nullable = false, length = 11)
	public Integer getIdKeywordTrack()
	{
		return this.idKeywordTrack;
	}

	public void setIdKeywordTrack(Integer idKeywordTrack)
	{
		this.idKeywordTrack = idKeywordTrack;
	}

	@Column(name = "KEYWORD", nullable = true, length = 65535)
	public String getKeyword()
	{
		return this.keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
		
	}

	@Column(name = "EXCEPTION", length = 5500)
	public String getException()
	{
		return exception;
	}

	public void setException(String exception)
	{
		this.exception = exception;
	}

	@Column(name = "ID_USER", length = 11)
	public Integer getIdUser()
	{
		return this.idUser;
	}

	public void setIdUser(Integer idUser)
	{
		this.idUser = idUser;
	}

	@Column(name = "NO_OF_MATCHES", length = 5500)
	public String getNoOfMatches()
	{
		return noOfMatches;
	}

	public void setNoOfMatches(String noOfMatches)
	{
		this.noOfMatches = noOfMatches;
	}

	@Column(name = "FORM", nullable = false, length = 512)
	public String getForm()
	{
		return form;
	}

	public void setForm(String form)
	{
		this.form = form;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 11)
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

	@Column(name = "UPDATED_BY", nullable = true, length = 11)
	public Integer getUpdatedBy()
	{
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	@Column(name = "UPDATED_ON", nullable = true)
	public Timestamp getUpdatedOn()
	{
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn)
	{
		this.updatedOn = updatedOn;
	}
	
	@Column(name = "COUNT", nullable = true)
	public Integer getCount()
	{
		return this.count;
	}

	public void setCount(Integer count)
	{
		this.count = count;
	}

	@Column(name = "URL", nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}