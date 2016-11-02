package com.crackers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.ApplicationConfig;

public interface ApplicationConfigRepository extends JpaRepository<ApplicationConfig, Integer>
{

	@Query("select ac.configValue from ApplicationConfig ac where ac.configKey like :configKey")
	String getConfigValueByKey(@Param("configKey") String configKey);

	@Query("select ac.configKey, ac.configValue from ApplicationConfig ac where ac.configKey in ( :configKey ) ")
	List<Object[]> getConfigValuesByKeys(@Param("configKey") List<String> configKey);
}