package com.crackers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer>
{

	@Query("select u from PhoneNumber u where u.user.idUser = :idUser and u.isDeleted =0")
	List<PhoneNumber> getUserPhoneNumbers(@Param("idUser") Integer idUser);
	
	@Query("select u from PhoneNumber u where u.isDeleted =0")
	List<PhoneNumber> getPhoneNumbers();
}