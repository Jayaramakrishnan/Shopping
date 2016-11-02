package com.crackers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.UserCredential;

public interface CredentialRepository extends JpaRepository<UserCredential, Long>
{

	@Query("select c from UserCredential c where c.idUser in (select u.idUser from User u where LOWER(u.userName )= LOWER(:userName) and u.idUserState = 1 and u.isDeleted = 0)")
	UserCredential getCredentialByUserName(@Param("userName") String userName);
	
	@Query("select c.hashedKey from UserCredential c where c.idUser= :idUser")
	String getCredentialByUserId(@Param("idUser") long idUser);
	
	@Query("select c from UserCredential c where c.idUser= :idUser and c.isDeleted = 0")
	UserCredential getCredentialObject(@Param("idUser") long idUser);
}