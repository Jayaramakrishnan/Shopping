package com.crackers.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.EmailTrack;

public interface EmailTrackRepository extends JpaRepository<EmailTrack, Integer>
{

	@Query("select e from EmailTrack e  where e.idRecipient = :idRecipient and e.createdBy= :createdBy and e.idGeneric = :idGeneric and e.idEmailTemplate= :template and e.createdOn between :start and :end")
	List<EmailTrack> getTrack(@Param("createdBy") Integer idUser, @Param("idRecipient") Integer idUser2, @Param("idGeneric") Integer idCase, @Param("start") Timestamp from, @Param("end") Timestamp to, @Param("template") Integer integer);
}