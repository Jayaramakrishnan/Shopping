package com.crackers.common;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JavaObjectUtil
{

    public static Boolean  printToLog  = true;
    private static Boolean convertJson = true;
    private static Logger  logger      = Logger.getLogger(JavaObjectUtil.class);

    public static void print(Object object)
    {
        try
        {
            if (printToLog)
            {
                CMSLogger.info(logger, "-------------------------START----------------------------------");
                CMSLogger.info(logger, convertObjectToJson(object));
                CMSLogger.info(logger, "--------------------------END-----------------------------------");
            }
        }
        catch (Exception exception)
        {
            CMSLogger.error(logger, "Exception", exception);
        }
    }

    public static String convertObjectToJson(Object object)
    {
        if (convertJson)
        {
            ObjectMapper mapper = getMapper();
            try
            {
                return mapper.writeValueAsString(object);
            }
            catch (JsonGenerationException jsonGenerationException)
            {
                CMSLogger.error(logger, "JsonGenerationException occured while print object", jsonGenerationException);
            }
            catch (JsonMappingException jsonMappingException)
            {
                CMSLogger.error(logger, "JsonMappingException occured while print object", jsonMappingException);
            }
            catch (IOException ioException)
            {
                CMSLogger.error(logger, "IOException occured while print object", ioException);
            }
        }
        return object.toString();
    }

    private static ObjectMapper getMapper()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationConfig.Feature.WRAP_ROOT_VALUE);
        mapper.enable(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY);
        mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        mapper.disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
        return mapper;
    }
}