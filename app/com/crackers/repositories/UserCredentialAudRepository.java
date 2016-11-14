package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.UserCredentialAud;

public interface UserCredentialAudRepository extends GraphRepository<UserCredentialAud>
{

    @Query("match (uca:UserCredentialAud) where uca.idUser = :idUser and uca.isDeleted = 0 order by uca.updatedOn DESC return uca")
    List<UserCredentialAud> verifyPreviousPasswords(@Param("idUser") Integer idUser);
}