package com.crackers.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

public class BeanUtil
{

	private static final Logger	LOGGER	= LoggerFactory.getLogger(BeanUtil.class);

	public static void copyBeanProperties(final Object source, final Object target, final Collection<String> includes) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		final Collection<String> excludes = Lists.newArrayList();
		copyBeanPropertiesExclude(source, target, includes, excludes);
	}

	public static void copyBeanPropertiesExclude(final Object source, final Object target, final Collection<String> includes, final Collection<String> excludes)
	{
		Collection<String> excludeList = excludes;
		if (excludeList == null)
		{
			excludeList = Lists.newArrayList();
		}
		final PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(source.getClass());
		for (final PropertyDescriptor propertyDescriptor : propertyDescriptors)
		{
			String propName = propertyDescriptor.getName();
			try
			{
				if (propertyDescriptor.getReadMethod().invoke(source) == null && (includes == null || !includes.contains(propName)))
				{
					excludeList.add(propName);
				}
			}
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				LOGGER.error("Exception occurred while copying data from source to target", e);
			}
		}
		BeanUtils.copyProperties(source, target, excludeList.toArray(new String[excludeList.size()]));
	}
}