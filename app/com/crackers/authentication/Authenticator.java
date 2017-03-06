package com.crackers.authentication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.crackers.model.User;

public interface Authenticator {

	boolean authenticate(User validUser, String password) throws InvalidKeyException, NoSuchAlgorithmException;
}