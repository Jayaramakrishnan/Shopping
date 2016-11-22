package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Email;

public interface EmailRepository extends GraphRepository<Email>
{

    @Query("match (email)<-[:HAS_AN_EMAIL]-(user:User) where user.idUser = {idUser} and email.isPrimary = 1 and email.isDeleted = 0 return email")
    Email getCreatedByMailId(@Param("idUser") Integer idUser);

    @Query("match (email)<-[:HAS_AN_EMAIL]-(user:User) where user.idUser = {idUser} and email.isDeleted = 0 return email")
    List<Email> getUsersMailId(@Param("idUser") Integer idUser);

    @Query("match (email)<-[:HAS_AN_EMAIL]-(user:User) where LOWER(email.email) like LOWER({email}) and user.idUserState = 1 and user.isDeleted = 0 and email.isDeleted = 0 return user.idUser")
    Integer validateNewUserMailId(@Param("email") String email);

    @Query("match (email)<-[:HAS_AN_EMAIL]-(user:User) where user.idUserState = 1 and user.idUser = {idUser} and email.isPrimary = 1 and user.isDeleted = 0 and email.isDeleted = 0 return email.email")
    String getEmailByIdUser(@Param("idUser") Integer idUser);

    @Query("match (emails:Email) where e.isDeleted = 0 return emails")
    List<Email> getEmails();
}