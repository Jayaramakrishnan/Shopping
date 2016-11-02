package com.crackers.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;

@Component
public class PassEncryptUtil
{

	private static Logger	logger	= Logger.getLogger(PassEncryptUtil.class);

	// method for encrypt the password with salt key, and generate the Hashed key for authentication.
	public String encryptPassword(String data, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException
	{
		/*
		 * It hold the secretKey Specifications of this encryption .
		 */
		SecretKeySpec signingKey = null;
		Mac mac = null;
		CMSLogger.info(logger, "Validation By  " + algorithm);
		signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
		mac = Mac.getInstance(algorithm);
		mac.init(signingKey);
		byte[] rawSig = mac.doFinal(data.getBytes());
		return new String(Hex.encodeHex(rawSig));
	}
}