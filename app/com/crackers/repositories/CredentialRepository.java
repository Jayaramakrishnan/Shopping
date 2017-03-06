package com.crackers.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.UserCredential;

public interface CredentialRepository extends GraphRepository<UserCredential> {

	@Query("match (uc:UserCredential)<-[:HAS_A_PASSWORD]-(user: User) where LOWER(user.userName) = LOWER({userName}) and user.isDeleted = 0 return uc")
	UserCredential getCredentialByUserName(@Param("userName") String userName);

	@Query("match (uc:UserCredential)<-[:HAS_A_PASSWORD]-(user: User) where user.id = {idUser} return uc.hashedKey")
	String getCredentialByUserId(@Param("idUser") Long idUser);

	@Query("match (uc:UserCredential)<-[:HAS_A_PASSWORD]-(user: User) where user.id = {idUser} and uc.isDeleted = 0 return uc")
	UserCredential getCredentialObject(@Param("idUser") Long idUser);
}