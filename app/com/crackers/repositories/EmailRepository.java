package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Email;

public interface EmailRepository extends GraphRepository<Email>
{

    @Query("match (e:Email) where e.user.idUser = :createdBy and e.isPrimary = 1 and e.isDeleted = 0 return e")
    Email getCreatedByMailId(@Param("createdBy") Integer createdBy);

    @Query("match (e:Email) where e.user.idUser = :idUser and e.isDeleted = 0 return e")
    List<Email> getUsersMailId(@Param("idUser") Integer idUser);

    @Query("match (e:Email) where e.user.idUser = :idUser and e.isPrimary = 1 and e.isDeleted = 0 return e")
    Email getPrimaryMail(@Param("idUser") Integer idUser);

    @Query("match (e:Email) where LOWER(e.email) like LOWER(:email) and e.user.idUserState = 1 and e.user.isDeleted = 0 and e.isDeleted = 0 return e.user.idUser")
    Integer validateNewUserMailId(@Param("email") String email);

    @Query("match (e:Email) where e.user.idUserState=1 and e.user.idUser = :idUser and e.isPrimary = 1 and e.user.isDeleted = 0 and e.isDeleted = 0 return e.email")
    String getEmailByIdUser(@Param("idUser") Integer idUser);

    @Query("match (e:Email) where e.isDeleted = 0 return e")
    List<Email> getEMails();
}