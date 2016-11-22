package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.ContactDetails;

public interface UserContactDetailsRepository extends GraphRepository<ContactDetails>
{

    @Query("match (addresses)<-[:HAS_MULTIPLE_ADDRESSES]-(user:User) where user.idUser = {idUser} and addresses.isDeleted = 0 return addresses")
    List<ContactDetails> getContactDetails(@Param("idUser") Integer idUser);

    @Query("match (cd:ContactDetails) where cd.isDeleted = 0")
    List<ContactDetails> getAllContactDetails();
}