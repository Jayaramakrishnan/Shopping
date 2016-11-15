package com.crackers.authentication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.crackers.model.User;
import com.crackers.model.UserSource;

public interface Authenticator
{

    boolean authenticate(User validUser, String password) throws InvalidKeyException, NoSuchAlgorithmException;

    List<UserSource> getUserSource();
}