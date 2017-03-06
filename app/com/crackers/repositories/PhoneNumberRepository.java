package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.PhoneNumber;

public interface PhoneNumberRepository extends GraphRepository<PhoneNumber> {

	@Query("match (phoneNumber:PhoneNumber)<-[:HAS_MULTIPLE_PHONE_NUMBERS]-(user:User) where user.id = {idUser} and phoneNumber.isDeleted = 0 return phoneNumber")
	List<PhoneNumber> getUserPhoneNumbers(@Param("idUser") Long idUser);

	@Query("match (pn:PhoneNumber) where pn.isDeleted = 0 return pn")
	List<PhoneNumber> getPhoneNumbers();
}