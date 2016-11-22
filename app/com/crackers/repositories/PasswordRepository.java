package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Password;

public interface PasswordRepository extends GraphRepository<Password>
{

    @Query("match (previousPassword)<-[:HAS_PREVIOUS_PASSWORDS]-(user:User) where user.idUser = {idUser} and previousPassword.isDeleted = 0 and previousPassword.isExpired = 0 return previousPassword")
    List<Password> getPasswordListById(@Param("idUser") Integer idUser);

    @Query("match (p:Password) where p.idPassword = {idPassword} and p.isDeleted = 0 and p.isExpired = 0 return p")
    Password getPasswordById(@Param("idPassword") Integer idPassword);

    @Query("match (p:Password) where p.idPassword = {idPassword} and p.isExpired = 1 return p")
    Password getPasswordExpiredObject(@Param("idPassword") Integer idPassword);
}