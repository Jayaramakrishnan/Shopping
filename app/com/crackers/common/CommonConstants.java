/**
 * @author rajagja
 * @date Nov 1, 2016
 */
package com.crackers.common;

public class CommonConstants
{

    public static org.springframework.context.ApplicationContext CONTEXT;
    // Email Properties Value
    public static final String                                   MAIL_TRANSPORT_PROTOCOL       = "mail.transport.protocol";
    public static final String                                   MAIL_SMTP_HOST                = "mail.smtp.host";
    public static final String                                   MAIL_SMTP_PORT                = "mail.smtp.port";
    public static final String                                   MAIL_SMTP_STARTTLS            = "mail.smtp.starttls.enable";
    public static final String                                   MAIL_SMTP_AUTH                = "mail.smtp.auth";
    public static final String                                   MAIL_SMTP_SSL                 = "mail.smtp.ssl.trust";
    // Device Constants
    public static final String                                   USER_DEVICE_SMART_PHONE       = "Smartphone";
    public static final String                                   USER_DEVICE_TABLET            = "Tablet";
    public static final String                                   USER_DEVICE_DESKTOP           = "Personal computer";
    public static final String                                   USER_AGENT_STRING             = "User-Agent";
    public static final String                                   UNIQUE_ID                     = "uniqueId";
    public static final String                                   USER_INFO                     = "userInfo";
    public static final String                                   REDIRECT_URL                  = "redirectUrl";
    public static final String                                   USER_DEVICE_STRING            = "User-Device";
    public static final String                                   CLIENT_IP_STRING              = "Client-Ip";
    public static final String                                   USER_ROLE_ID                  = "ID_ROLE";
    public static final String                                   USER_NAME                     = "USER_NAME";
    public static final String                                   USER_FIRST_NAME               = "USER_FIRST_NAME";
    public static final String                                   USER_LAST_NAME                = "USER_LAST_NAME";
    public static final int                                      INTITIAL_VALUE                = 0;
    public static final int                                      EMPTY_LIST                    = 0;
    public static final int                                      FIRST_VAULE                   = 1;
    public static final String                                   CLIENT                        = "client";
    public static final Integer                                  FORM_USER                     = 1;
    public static final Integer                                  LDAP_USER                     = 3;
    public static final Integer                                  AD_USER                       = 2;
    public static final String                                   VERSION                       = "version";
    // Dynamic Form Input Type Constants
    public static final String                                   DROPDOWN                      = "Dropdown";
    public static final String                                   MULTI_SELECT_DROPDOWN         = "MultiSelectDropdown";
    public static final String                                   RADIO                         = "Radio";
    public static final String                                   DATE                          = "dates";
    // Date Format throughout the application
    public static String                                         DATE_FORMAT;
    public static String                                         IS_TIME_REQUIRED;
    // Authentication types
    public static final String                                   FORMS_AUTHENTICATION          = "FORM";
    // Encryption algorithm type.
    public static final String                                   ENCRYPTION_ALGORITHM          = "HmacSHA1";
    public static final String                                   SALT_KEY                      = "salt1#";
    public static final String                                   PREVIOUS_PASSWORD_CHECK_COUNT = "3";
    // Email Template Constants
    public static final Integer                                  NEW_PASSWORD                  = 6;
    public static final Integer                                  FORGET_PASSWORD               = 7;
    public static final Integer                                  RESET_PASSWORD                = 8;
    public static final String                                   POWERED_BY_XXX                = "POWERED_BY_XXX";
    public static final String                                   SUPPORT_EMAIL_ID              = "SUPPORT_EMAIL_ID";
    public static final String                                   IS_EMAIL_ON                   = "isEmailOn";
    public static final String                                   EMAIL_ACCESS_USERNAME         = "emailAccessUsername";
    public static final String                                   EMAIL_ACCESS_PASSWORD         = "emailAccessPassword";
    public static final Short                                    IS_DELETED                    = 0;
}