package com.crackers.util;

import org.apache.log4j.Logger;

import com.crackers.common.CrackersLogger;

import play.api.libs.Crypto;

public class CryptoBinderUtil
{

    private static Logger logger = Logger.getLogger(CryptoBinderUtil.class);

    public static String getEncryptId(String id)
    {
        String encryptId = null;
        if (id != null)
        {
            try
            {
                encryptId = Crypto.encryptAES(id);
            }
            catch (Exception e)
            {
                CrackersLogger.error(logger, "Exception while getting Encrypting by id", e);
            }
        }
        return encryptId;
    }

    public static Long getDecryptId(String id)
    {
    	Long idDecrypt = null;
        if (id != null)
        {
            try
            {
                String decryptId = Crypto.decryptAES(id);
                idDecrypt = Long.parseLong(decryptId);
            }
            catch (Exception e)
            {
                CrackersLogger.error(logger, "Exception while getting Decrypting by id", e);
                return null;
            }
        }
        return idDecrypt;
    }

    public static String getDecryptedString(String id)
    {
        String idDecrypt = null;
        if (id != null)
        {
            try
            {
                idDecrypt = Crypto.decryptAES(id);
            }
            catch (Exception e)
            {
                CrackersLogger.error(logger, "Exception while getting Decrypting by id", e);
            }
        }
        return idDecrypt;
    }
}
