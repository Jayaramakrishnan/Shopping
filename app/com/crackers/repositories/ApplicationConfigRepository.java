package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.ApplicationConfig;

public interface ApplicationConfigRepository extends GraphRepository<ApplicationConfig>
{

    @Query("match (ac:ApplicationConfig) where ac.configKey like :configKey return ac.configValue")
    String getConfigValueByKey(@Param("configKey") String configKey);

    @Query("match (ac:ApplicationConfig) where ac.configKey in (:configKey) return ac.configKey, ac.configValue")
    List<Object[]> getConfigValuesByKeys(@Param("configKey") List<String> configKey);
}