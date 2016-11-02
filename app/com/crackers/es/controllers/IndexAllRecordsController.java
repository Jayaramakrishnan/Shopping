/**
 * @author rajagja
 * @date Nov 2, 2016
 */
package com.crackers.es.controllers;

import org.elasticsearch.action.get.GetResponse;
import org.springframework.stereotype.Component;

import com.crackers.controllers.BaseController;
import com.crackers.es.ESConstants;
import com.crackers.es.doc.UserDoc;
import com.google.gson.Gson;

@Component
public class IndexAllRecordsController extends BaseController
{

	public UserDoc getUserSettingsIndexDocById(String id)
	{
		GetResponse getResponse = ESConstants.client.prepareGet(ESConstants.index, ESConstants.userType, id).execute().actionGet();
		Gson gson = new Gson();
		UserDoc userDoc = gson.fromJson(getResponse.getSourceAsString(), UserDoc.class);
		return userDoc;
	}
}