package com.crackers.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.UserCredential;

public interface CredentialRepository extends GraphRepository<UserCredential>
{

    @Query("match (c:UserCredential) where c.idUser in (match (u:User) where LOWER(u.userName) = LOWER(:userName) and u.idUserState = 1 and u.isDeleted = 0 return u.idUser) return c")
    UserCredential getCredentialByUserName(@Param("userName") String userName);

    @Query("match (c:UserCredential) where c.idUser= :idUser return c.hashedKey")
    String getCredentialByUserId(@Param("idUser") long idUser);

    @Query("match (c:UserCredential) where c.idUser= :idUser and c.isDeleted = 0 return c")
    UserCredential getCredentialObject(@Param("idUser") long idUser);
}