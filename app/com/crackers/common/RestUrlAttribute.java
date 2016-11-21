package com.crackers.common;

public class RestUrlAttribute
{

    public static String        REST_BASE_URL              = "";
    public static final String  AUTHENTICATE               = "authenticate.url";
    public static final String  USER_INFO                  = "userinfo.url";
    public static final String  IS_PROXY                   = "is.proxy";
    public static final String  PROXY_USER                 = "proxy.user";
    public static final String  PROXY_PASSWORD             = "proxy.password";
    public static final String  AUTHORIZATION              = "Authorization";
    public static final String  FORMS_AUTHENTICATION       = "FORM";
    // REST Timeout
    public static final Integer REST_WAIT_TIME             = 50000;
    // REST Url's
    public static final String  EMPTY_QUOTES               = "";
    // User Setting
    public static final String  NEW_USER_POST              = "new.user.post.url";
    public static final String  USER_PUT                   = "user.put.url";
    // Password
    public static final String  NEW_USER_VALIDATE_PASSWORD = "new.user.validate.password.post.url";
    public static final String  CREATE_NEW_LOGIN_PASSWORD  = "create.new.login.password.post.url";
    public static final String  CREATE_FORGET_PASSWORD     = "create.forget.password.post.url";
    public static final String  CREATE_RESET_PASSWORD      = "create.reset.password.post.url";
    public static final String  FORGOT_USER_VALIDATE_EMAIL = "forgot.user.validate.email.get.url";
}