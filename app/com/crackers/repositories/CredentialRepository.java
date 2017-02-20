package com.crackers.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.UserCredential;

public interface CredentialRepository extends GraphRepository<UserCredential>
{

    @Query("match (password)<-[:HAS_A_PASSWORD]-(user:User) where LOWER(user.userName) = LOWER({userName}) and user.idUserState = 1 and user.isDeleted = 0 return password")
    UserCredential getCredentialByUserName(@Param("userName") String userName);

    @Query("match (password)<-[:HAS_A_PASSWORD]-(user:User) where user.idUser = {idUser} return password.hashedKey")
    String getCredentialByUserId(@Param("idUser") Long idUser);

    @Query("match (password)<-[:HAS_A_PASSWORD]-(user:User) where user.idUser = {idUser} and password.isDeleted = 0 return password")
    UserCredential getCredentialObject(@Param("idUser") Long idUser);
    
    @Query("match (uc:UserCredential) where uc.idUserCredential = {idUserCredential} return uc")
    UserCredential getCredentialById(@Param("idUserCredential") Integer idUserCredential);
}