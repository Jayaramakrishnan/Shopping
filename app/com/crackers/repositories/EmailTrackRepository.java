package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.EmailTrack;

public interface EmailTrackRepository extends GraphRepository<EmailTrack> {

	@Query("match (e:EmailTrack) where e.idRecipient = {idRecipient} and e.createdBy = {createdBy} and e.idGeneric = {idGeneric} and e.idEmailTemplate = {template} and e.createdOn between {start} and {end} return e")
	List<EmailTrack> getTrack(@Param("createdBy") Integer createdBy, @Param("idRecipient") Integer idRecipient, @Param("idGeneric") Integer idGeneric, @Param("start") Long start, @Param("end") Long end, @Param("template") Integer template);
}