package com.crackers.es;

import java.io.File;

import org.elasticsearch.client.Client;

//import org.elasticsearch.client.Client;
public class ESConstants
{

	// Application ES Settings
	public static final String	ES_SERVER_HOST_AND_PORT				= "ES_SERVER_HOST_AND_PORT";
	// Settings
	public static final String	CLUSTER_NAME						= "clusterName";
	public static final String	CLUSTER_VALUE						= "clusterValue";
	public static final String	SCRIPT_INLINE						= "script.inline";
	public static final String	SCRIPT_INDEXED						= "script.indexed";
	public static final String	SCRIPT_ON							= "on";
	// Indexes
	public static final String	INDEX								= "index";
	// Mappings
	public static final String	USER_MAPPING						= ("User" + File.separator + "user.json").replace("\\", "/");
	// Config
	public static final String	SCAN_AND_SCROLL_SIZE				= "scanAndScrollSize";
	public static final String	CLIENT								= "client";
	public static final String	LOCAL								= "local";
	public static final String	TRANSPORT							= "transport";
	public static final String	TRANSPORT_IP						= "transportIp";
	public static final String	TRANSPORT_PORT						= "transportPort";
	// Types
	public static final String	USER_TYPE							= "userType";
	public static final String	SHINGLE_ANALYZER					= "shingle_analyzer";
	public static final String	SNOWBALL_ANALYZER					= "snowball_analyzer";
	public static final String	MY_NGRAM_ANALYZER					= "my_ngram_analyzer";
	public static final String	EXACT_SHINGLE_ANALYZER				= "exact_shingle_analyzer";
	public static final String	WHITE_SPACE_ANALYZER				= "whitespace_analyzer";
	// Client
	public static Client		client;
	public static String		esServerHostAndPort;
	public static String		index;
	public static String		userType;
	// Date Format
	public static final String	ES_DATE_FORMAT						= "yyyy-MM-dd'T'HH:mm:ss.S'Z'";
	public static final String	USER_SETTINGS						= "USER_SETTINGS_";
	// User settings Constants
	public static final String	USER_SETTINGS_NESTED_PATH			= "users";
	public static final String	USER_SETTINGS_ID_USER_STATE			= "users.userStateDto.idUserState";
	public static final String	USER_SETTINGS_FIRST_NAME_EXACT		= "users.firstName.exact";
	public static final String	USER_SETTINGS_FIRST_NAME_NGRAM		= "users.firstName.my_ngram_title";
	public static final String	USER_SETTINGS_FIRST_NAME_LOWERCASE	= "users.firstName.lowercase";
	public static final String	USER_SETTINGS_FIRST_NAME_RAW		= "users.firstName.raw";
	public static final String	USER_SETTINGS_LAST_NAME_EXACT		= "users.lastName.exact";
	public static final String	USER_SETTINGS_LAST_NAME_NGRAM		= "users.lastName.my_ngram_title";
	public static final String	USER_SETTINGS_LAST_NAME_LOWERCASE	= "users.lastName.lowercase";
	public static final String	USER_SETTINGS_LAST_NAME_RAW			= "users.lastName.raw";
	public static final String	USER_SETTINGS_FULL_NAME_EXACT		= "users.fullName.exact";
	public static final String	USER_SETTINGS_FULL_NAME_NGRAM		= "users.fullName.my_ngram_title";
	public static final String	USER_SETTINGS_FULL_NAME_LOWERCASE	= "users.fullName.lowercase";
	public static final String	USER_SETTINGS_FULL_NAME_RAW			= "users.fullName.raw";
	public static final String	USER_SETTINGS_USER_NAME_RAW			= "users.userName.raw";
	public static final String	USER_SETTINGS_USER_NAME_EXACT		= "users.userName.exact";
	public static final String	USER_SETTINGS_USER_NAME_NGRAM		= "users.userName.my_ngram_title";
	public static final String	USER_SETTINGS_USER_NAME_LOWERCASE	= "users.userName.lowercase";
	public static final String	USER_SETTINGS_EMAIL_EXACT			= "users.emailDtos.email.exact";
	public static final String	USER_SETTINGS_EMAIL_RAW				= "users.emailDtos.email.raw";
	public static final String	USER_SETTINGS_EMAIL_REVERSE			= "users.emailDtos.email.reverse";
	public static final String	USER_SETTINGS_EMAIL_NGRAM			= "users.emailDtos.email.ngram";
	public static final String	USER_SETTINGS_EMAIL_ANALYZER		= "users.emailDtos.email.emailAnalyzer";
	public static final String	USER_SETTINGS_PHONE_EXACT			= "users.phoneNumberDtos.phoneNumber.exact";
	public static final String	USER_SETTINGS_PHONE_RAW				= "users.phoneNumberDtos.phoneNumber.raw";
	public static final String	USER_SETTINGS_PHONE_REVERSE			= "users.phoneNumberDtos.phoneNumber.reverse";
	public static final String	USER_SETTINGS_PHONE_NGRAM			= "users.phoneNumberDtos.phoneNumber.ngram";
	public static final String	USER_SETTINGS_PINCODE_RAW			= "users.contactDetailsDtos.pincode.raw";
	public static final String	USER_SETTINGS_PINCODE_NGRAM			= "users.contactDetailsDtos.pincode.ngram";
	public static final String	USER_SETTINGS_PINCODE_EXACT			= "users.contactDetailsDtos.pincode.exact";
	public static final String	USER_SETTINGS_STATE_RAW				= "users.contactDetailsDtos.state.raw";
	public static final String	USER_SETTINGS_STATE_NGRAM			= "users.contactDetailsDtos.state.ngram";
	public static final String	USER_SETTINGS_STATE_EXACT			= "users.contactDetailsDtos.state.exact";
	public static final String	USER_SETTINGS_CITY_RAW				= "users.contactDetailsDtos.city.raw";
	public static final String	USER_SETTINGS_CITY_NGRAM			= "users.contactDetailsDtos.city.ngram";
	public static final String	USER_SETTINGS_CITY_EXACT			= "users.contactDetailsDtos.city.exact";
	public static final String	USER_SETTINGS_STREET_RAW			= "users.contactDetailsDtos.street.raw";
	public static final String	USER_SETTINGS_STREET_NGRAM			= "users.contactDetailsDtos.street.ngram";
	public static final String	USER_SETTINGS_STREET_EXACT			= "users.contactDetailsDtos.street.exact";
	public static final String	USER_SETTINGS_BIODATA_NGRAM			= "users.bioData.analyzed_summary";
	public static final String	USER_SETTINGS_BIODATA_SNOWBALL		= "users.bioData.snowball_summary";
	public static final String	USER_SETTINGS_BIODATA_RAW			= "users.bioData.raw";
	public static final String	USER_SETTINGS_BIODATA_EXACT			= "users.bioData.exact";
	public static final String	USER_SETTINGS_ACCESSIBLES_USERS		= "accessibleUsers";
	public static final String	USER_SETTINGS_ROLE_RAW				= "users.roleDto.role.raw";
	public static final String	USER_SETTINGS_ROLE_LOWERCASE		= "users.roleDto.role.lowercase";
	public static final String	USER_SETTINGS_ROLE_NGRAM			= "users.roleDto.role.ngram";
	public static final String	USER_SETTINGS_ROLE_EXACT			= "users.roleDto.role.exact";
	// Index Attachments
	public static final String	CREATED_ON							= "createdOn";													// Dont
																																	// Change
																																	// this
																																	// value
	public static final String	LAST_UPDATED_ON_ES					= "lastUpdatedOnES";
	// Session Variable for Pagination Calculation
	public static final String	WORDCOUNT							= "WORDCOUNT_";
}
