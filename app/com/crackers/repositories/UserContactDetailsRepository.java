package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.ContactDetails;

public interface UserContactDetailsRepository extends GraphRepository<ContactDetails>
{

    @Query("match (cd:ContactDetails) where cd.user.idUser= :id and cd.isDeleted= 0 return cd")
    List<ContactDetails> getContactDetails(@Param("id") Integer idUser);

    @Query("match (cd:ContactDetails) where cd.isDeleted = 0")
    List<ContactDetails> getAllContactDetails();
}