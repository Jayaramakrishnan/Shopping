package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Email;

public interface EmailRepository extends GraphRepository<Email> {

	@Query("match (email:Email)<-[:HAS_MULTIPLE_EMAILS]-(user:User) where user.id = {idUser} and email.isPrimary = 1 and email.isDeleted = 0 return email")
	Email getCreatedByMailId(@Param("idUser") Long idUser);

	@Query("match (email:Email)<-[:HAS_MULTIPLE_EMAILS]-(user:User) where user.id = {idUser} and email.isDeleted = 0 return email")
	List<Email> getUsersMailId(@Param("idUser") Long idUser);

	@Query("match (email:Email)<-[:HAS_MULTIPLE_EMAILS]-(user:User) where LOWER(email.email) =~ ('.*'+LOEWR({email})+'*.') and user.isDeleted = 0 and email.isDeleted = 0 return user.id")
	Long validateNewUserMailId(@Param("email") String email);
	
	@Query("match (email:Email)<-[:HAS_MULTIPLE_EMAILS]-(user:User) where user.id = {idUser} and email.isPrimary = 1 and user.isDeleted = 0 and email.isDeleted = 0 return email.email")
	String getEmailByIdUser(@Param("idUser") Long idUser);

	@Query("match (emails:Email) where e.isDeleted = 0 return emails")
	List<Email> getEmails();
}