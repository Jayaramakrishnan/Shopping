/**
 * @author rajagja
 * @date Nov 2, 2016
 */
package com.crackers.manager.es;

import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.es.doc.UserDoc;
import com.google.gson.Gson;

@Component
public class IndexAllRecordsManager
{

	private static Logger	logger	= Logger.getLogger(IndexAllRecordsManager.class);

	public boolean createUserSettingsIndexById(Client client, UserDoc userDoc, String idUser, String index, String type)
	{
		Gson gson = new Gson();
		String value = gson.toJson(userDoc);
		IndexResponse indexResponse = client.prepareIndex(index, type, idUser).setSource(value).execute().actionGet();
		client.admin().indices().prepareRefresh().execute().actionGet();
		CMSLogger.info(logger, "indexResponse.getId() of UserSettings " + indexResponse.getId());
		return indexResponse.isCreated();
	}
}