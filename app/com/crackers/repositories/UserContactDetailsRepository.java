package com.crackers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.ContactDetails;

public interface UserContactDetailsRepository extends JpaRepository<ContactDetails, Integer>
{

	@Query("select r from ContactDetails r where r.user.idUser= :id and  r.isDeleted= 0")
	List<ContactDetails> getContactDetails(@Param("id") Integer idUser);

	@Query("select r from ContactDetails r where r.isDeleted= 0")
	List<ContactDetails> getAllContactDetails();
}