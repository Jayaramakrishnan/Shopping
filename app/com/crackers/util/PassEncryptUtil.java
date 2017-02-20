package com.crackers.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CrackersLogger;

@Component
public class PassEncryptUtil
{

    private static Logger logger = Logger.getLogger(PassEncryptUtil.class);

    public String encryptPassword(String data, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException
    {
        CrackersLogger.info(logger, "Validation By  " + algorithm);
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);
        byte[] rawSig = mac.doFinal(data.getBytes());
        return new String(Hex.encodeHex(rawSig));
    }
}