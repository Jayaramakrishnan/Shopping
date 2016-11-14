package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.PhoneNumber;

public interface PhoneNumberRepository extends GraphRepository<PhoneNumber>
{

    @Query("match (pn:PhoneNumber) where pn.user.idUser = :idUser and pn.isDeleted = 0 return pn")
    List<PhoneNumber> getUserPhoneNumbers(@Param("idUser") Integer idUser);

    @Query("match (pn:PhoneNumber) where pn.isDeleted = 0 return pn")
    List<PhoneNumber> getPhoneNumbers();
}