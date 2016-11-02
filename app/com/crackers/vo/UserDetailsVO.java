package com.crackers.vo;

import java.util.List;

public class UserDetailsVO
{

	private String					idUser;
	private String					firstName;
	private String					lastName;
	private Integer					idRole;
	private String					role;
	private String					bioData;
	private List<ContactDetailsVO>	contactDetailsVos;
	private List<PhoneVO>			phoneNumberVos;
	private List<EmailVO>			emailVos;
	private Integer					idSource;
	private String					source;
	private Integer					idUserState;
	private String					state;
	private Integer					firstNameSource;
	private Integer					lastNameSource;
	private Integer					bioDataSource;
	private String					smaAccountName;
	private String					uniqueId;
	private Long					count;
	private Integer					imageIsAvailable;
	private String					imageColorCode;
	private Integer					idImageColorCode;

	public String getIdUser()
	{
		return idUser;
	}

	public void setIdUser(String idUser)
	{
		this.idUser = idUser;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
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

	public String getBioData()
	{
		return bioData;
	}

	public void setBioData(String bioData)
	{
		this.bioData = bioData;
	}

	public List<ContactDetailsVO> getContactDetailsVos()
	{
		return contactDetailsVos;
	}

	public void setContactDetailsVos(List<ContactDetailsVO> contactDetailsVos)
	{
		this.contactDetailsVos = contactDetailsVos;
	}

	public List<PhoneVO> getPhoneNumberVos()
	{
		return phoneNumberVos;
	}

	public void setPhoneNumberVos(List<PhoneVO> phoneNumberVos)
	{
		this.phoneNumberVos = phoneNumberVos;
	}

	public List<EmailVO> getEmailVos()
	{
		return emailVos;
	}

	public void setEmailVos(List<EmailVO> emailVos)
	{
		this.emailVos = emailVos;
	}

	public Integer getIdSource()
	{
		return idSource;
	}

	public void setIdSource(Integer idSource)
	{
		this.idSource = idSource;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public Integer getIdUserState()
	{
		return idUserState;
	}

	public void setIdUserState(Integer idUserState)
	{
		this.idUserState = idUserState;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Integer getFirstNameSource()
	{
		return firstNameSource;
	}

	public void setFirstNameSource(Integer firstNameSource)
	{
		this.firstNameSource = firstNameSource;
	}

	public Integer getLastNameSource()
	{
		return lastNameSource;
	}

	public void setLastNameSource(Integer lastNameSource)
	{
		this.lastNameSource = lastNameSource;
	}

	public Integer getBioDataSource()
	{
		return bioDataSource;
	}

	public void setBioDataSource(Integer bioDataSource)
	{
		this.bioDataSource = bioDataSource;
	}

	public String getSmaAccountName()
	{
		return smaAccountName;
	}

	public void setSmaAccountName(String smaAccountName)
	{
		this.smaAccountName = smaAccountName;
	}

	public String getUniqueId()
	{
		return uniqueId;
	}

	public void setUniqueId(String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	public Long getCount()
	{
		return count;
	}

	public void setCount(Long count)
	{
		this.count = count;
	}

	public Integer getImageIsAvailable()
	{
		return imageIsAvailable;
	}

	public void setImageIsAvailable(Integer imageIsAvailable)
	{
		this.imageIsAvailable = imageIsAvailable;
	}

	public String getImageColorCode()
	{
		return imageColorCode;
	}

	public void setImageColorCode(String imageColorCode)
	{
		this.imageColorCode = imageColorCode;
	}

	public Integer getIdImageColorCode()
	{
		return idImageColorCode;
	}

	public void setIdImageColorCode(Integer idImageColorCode)
	{
		this.idImageColorCode = idImageColorCode;
	}
}