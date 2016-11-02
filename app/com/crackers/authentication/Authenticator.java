package com.crackers.authentication;

import java.util.List;

import com.crackers.model.User;
import com.crackers.model.UserSource;

public interface Authenticator
{

	boolean authenticate(User validUser, String password) throws Exception;

	List<UserSource> getUserSource();
}