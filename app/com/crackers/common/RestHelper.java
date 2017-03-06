package com.crackers.common;

import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;

public class RestHelper {

	/**
	 * check proxy setting if proxy is set
	 * 
	 * @param requestHolder
	 * @return
	 */
	public static WSRequestHolder checkProxyAndSetHeader(String url) {
		return WS.url(url);
	}
}
