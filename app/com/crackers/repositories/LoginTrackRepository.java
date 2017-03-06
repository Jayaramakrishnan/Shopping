package com.crackers.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.LoginTrack;

public interface LoginTrackRepository extends GraphRepository<LoginTrack> {

	@Query("match (lt:LoginTrack) where lt.sessionToken = {uniqueId} return lt")
	LoginTrack getLoginTrack(@Param("uniqueId") String uniqueId);
}