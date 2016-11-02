package com.crackers.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import play.cache.Cache;

import com.crackers.model.Role;
import com.crackers.model.User;

@Component
public class CacheManager
{

	public static final String	ID_USER			= "ID_USER_";
	public static final String	USER_INFO		= "USER_INFO_";
	public static final String	USER_ROLE		= "USER_ROLE_";
	public static final String	USER_TOKEN		= "USER_TOKEN_";
	public static final String	FUNCTION_ACCESS	= "FUNCTION_ACCESS_";

	// Set, get remove and refresh id user in cache
	public static void setIdUserToCache(String uniqueId, Integer idUser)
	{
		Cache.set(ID_USER + uniqueId, idUser);
	}

	public static Integer getIdUserFromCache(String uniqueId)
	{
		Object idUser = Cache.get(ID_USER + uniqueId);
		return (Integer) idUser;
	}

	public static void removeIdUserFromCache(String uniqueId)
	{
		Cache.remove(ID_USER + uniqueId);
	}

	public static void refreshIdUserCache(String uniqueId)
	{
		setIdUserToCache(uniqueId, getIdUserFromCache(uniqueId));
	}

	// Set, get remove and refresh function access in cache
	public static void setFunctionAccessToCache(String uniqueId, Map<Integer, List<Integer>> resourceMap)
	{
		Cache.set(FUNCTION_ACCESS + uniqueId, resourceMap);
	}

	@SuppressWarnings("unchecked")
	public static Map<Integer, List<Integer>> getFunctionAccessFromCache(String uniqueId)
	{
		Object obj = Cache.get(FUNCTION_ACCESS + uniqueId);
		return (Map<Integer, List<Integer>>) obj;
	}

	public static void removeFunctionAccessFromCache(String uniqueId)
	{
		Cache.remove(FUNCTION_ACCESS + uniqueId);
	}

	public static void refreshFunctionAccessCache(String uniqueId)
	{
		setFunctionAccessToCache(uniqueId, getFunctionAccessFromCache(uniqueId));
	}

	// Set, get remove and refresh user info in cache
	public static void setUserInfoToCache(String uniqueId, User userInfo)
	{
		Cache.set(USER_INFO + uniqueId, userInfo);
	}

	public static User getUserInfoFromCache(String uniqueId)
	{
		Object obj = Cache.get(USER_INFO + uniqueId);
		return (User) obj;
	}

	public static void removeUserInfoFromCache(String uniqueId)
	{
		Cache.remove(USER_INFO + uniqueId);
	}

	public static void refreshUserInfoCache(String uniqueId)
	{
		setUserInfoToCache(uniqueId, getUserInfoFromCache(uniqueId));
	}

	// Set, get remove and refresh user role in cache
	public static void setUserRoleToCache(String uniqueId, Role roles)
	{
		Cache.set(USER_ROLE + uniqueId, roles);
	}

	public static Role getUserRoleFromCache(String uniqueId)
	{
		Object obj = Cache.get(USER_ROLE + uniqueId);
		return (Role) obj;
	}

	public static void removeUserRoleFromCache(String uniqueId)
	{
		Cache.remove(USER_ROLE + uniqueId);
	}

	public static void refreshUserRoleCache(String uniqueId)
	{
		setUserRoleToCache(uniqueId, getUserRoleFromCache(uniqueId));
	}

	// Set, get remove and refresh user token in cache
	public static void addUserTokenToCache(Integer idUser, String token)
	{
		List<String> tokens = getUserTokenFromCache(idUser);
		if (tokens == null)
		{
			tokens = new ArrayList<>();
		}
		if (token != null)
		{
			tokens.add(token);
		}
		Cache.set(USER_TOKEN + idUser, tokens);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getUserTokenFromCache(Integer idUser)
	{
		Object obj = Cache.get(USER_TOKEN + idUser);
		return (List<String>) obj;
	}

	public static void removeUserTokenForIdUserFromCache(String uniqueId)
	{
		Integer idUser = getIdUserFromCache(uniqueId);
		List<String> tokens = getUserTokenFromCache(idUser);
		tokens.remove(uniqueId);
		if (tokens.size() == 0)
		{
			Cache.remove(USER_TOKEN + uniqueId);
		}
		else
		{
			Cache.set(USER_TOKEN, tokens);
		}
	}

	public static void refreshUserTokenCache(Integer idUser)
	{
		addUserTokenToCache(idUser, null);
	}

	public static void refreshAllCache(String uniqueId)
	{
		refreshUserTokenCache(getIdUserFromCache(uniqueId));
		refreshUserRoleCache(uniqueId);
		refreshUserInfoCache(uniqueId);
		refreshFunctionAccessCache(uniqueId);
		refreshIdUserCache(uniqueId);
	}

	public static void removeAllFromCache(String uniqueId)
	{
		removeUserTokenForIdUserFromCache(uniqueId);
		removeUserRoleFromCache(uniqueId);
		removeUserInfoFromCache(uniqueId);
		removeFunctionAccessFromCache(uniqueId);
		removeIdUserFromCache(uniqueId);
	}

	public static void removeAllForIdUserFromCache(Integer idUser)
	{
		List<String> tokens = getUserTokenFromCache(idUser);
		Iterator<String> tokensIterator = tokens.iterator();
		while (tokensIterator.hasNext())
		{
			removeAllFromCache(tokensIterator.next());
		}
	}
}