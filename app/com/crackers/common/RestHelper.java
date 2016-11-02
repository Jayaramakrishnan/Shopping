package com.crackers.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.Play;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;

import com.ning.http.util.Base64;

public class RestHelper
{

	private static final Logger	LOGGER	= LoggerFactory.getLogger(RestHelper.class);

	/**
	 * check proxy setting if proxy is set
	 * 
	 * @param requestHolder
	 * @return
	 */
	public static WSRequestHolder checkProxyAndSetHeader(String url)
	{
		if (Play.application().configuration().getBoolean(RestUrlAttribute.IS_PROXY))
		{
			LOGGER.info("Rest url requires proxy.");
			String encoded = new String(Base64.encode(new String(Play.application().configuration().getString(RestUrlAttribute.PROXY_USER) + ":" + Play.application().configuration().getString(RestUrlAttribute.PROXY_PASSWORD)).getBytes()));
			return WS.url(url).setHeader("Proxy-Authorization", "Basic " + encoded);
		}
		return WS.url(url);
	}
}
