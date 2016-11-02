package com.crackers.dto;

import java.util.List;

public class UserDto implements Cloneable
{

	private String					idUser;
	private RoleDto					roleDto;
	private String					firstName;
	private String					fullName;
	private String					lastName;
	private String					userName;
	private ImageDto				imageDto;
	private UserSourceDto			userSourceDto;
	private Short					isDeleted;
	private String					smaAccountName;
	private String					uniqueId;
	private Integer					uniqueIdSource;
	private String					title;
	private String					bioData;
	private List<ContactDetailsDto>	contactDetailsDtos;
	private List<PhoneNumberDto>	phoneNumberDtos;
	private List<EmailDto>			emailDtos;
	private List<String>			changedList;
	private UserStateDto			userStateDto;
	private Integer					firstNameSource;
	private Integer					lastNameSource;
	private Integer					bioDataSource;
	private Long					count;
	private String					oldPassword;
	private String					newPassword;
	private String					email;
	private Integer					idFlagPassword;
	private String					imageColorCode;
	private Integer					idImageColorCode;
	private Short					isRegistered;
	private Short					imageIsAvailable;

	public String getIdUser()
	{
		return idUser;
	}

	public void setIdUser(String idUser)
	{
		this.idUser = idUser;
	}

	public RoleDto getRoleDto()
	{
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto)
	{
		this.roleDto = roleDto;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public ImageDto getImageDto()
	{
		return imageDto;
	}

	public void setImageDto(ImageDto imageDto)
	{
		this.imageDto = imageDto;
	}

	public UserSourceDto getUserSourceDto()
	{
		return userSourceDto;
	}

	public void setUserSourceDto(UserSourceDto userSourceDto)
	{
		this.userSourceDto = userSourceDto;
	}

	public Short getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted)
	{
		this.isDeleted = isDeleted;
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

	public Integer getUniqueIdSource()
	{
		return uniqueIdSource;
	}

	public void setUniqueIdSource(Integer uniqueIdSource)
	{
		this.uniqueIdSource = uniqueIdSource;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getBioData()
	{
		return bioData;
	}

	public void setBioData(String bioData)
	{
		this.bioData = bioData;
	}

	public List<ContactDetailsDto> getContactDetailsDtos()
	{
		return contactDetailsDtos;
	}

	public void setContactDetailsDtos(List<ContactDetailsDto> contactDetailsDtos)
	{
		this.contactDetailsDtos = contactDetailsDtos;
	}

	public List<PhoneNumberDto> getPhoneNumberDtos()
	{
		return phoneNumberDtos;
	}

	public void setPhoneNumberDtos(List<PhoneNumberDto> phoneNumberDtos)
	{
		this.phoneNumberDtos = phoneNumberDtos;
	}

	public List<EmailDto> getEmailDtos()
	{
		return emailDtos;
	}

	public void setEmailDtos(List<EmailDto> emailDtos)
	{
		this.emailDtos = emailDtos;
	}

	public List<String> getChangedList()
	{
		return changedList;
	}

	public void setChangedList(List<String> changedList)
	{
		this.changedList = changedList;
	}

	public UserStateDto getUserStateDto()
	{
		return userStateDto;
	}

	public void setUserStateDto(UserStateDto userStateDto)
	{
		this.userStateDto = userStateDto;
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

	public Long getCount()
	{
		return count;
	}

	public void setCount(Long count)
	{
		this.count = count;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getIdFlagPassword()
	{
		return idFlagPassword;
	}

	public void setIdFlagPassword(Integer idFlagPassword)
	{
		this.idFlagPassword = idFlagPassword;
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

	public Short getIsRegistered()
	{
		return isRegistered;
	}

	public void setIsRegistered(Short isRegistered)
	{
		this.isRegistered = isRegistered;
	}

	public Short getImageIsAvailable()
	{
		return imageIsAvailable;
	}

	public void setImageIsAvailable(Short imageIsAvailable)
	{
		this.imageIsAvailable = imageIsAvailable;
	}
}