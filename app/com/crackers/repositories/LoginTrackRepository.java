package com.crackers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.LoginTrack;

public interface LoginTrackRepository extends JpaRepository<LoginTrack, Integer>
{

	@Query("select lt from LoginTrack lt where lt.sessionToken like :uniqueId")
	LoginTrack getLoginTrack(@Param("uniqueId") String uniqueId);
}