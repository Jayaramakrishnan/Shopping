package com.crackers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Password;

public interface PasswordRepository extends JpaRepository<Password, Integer>
{

	@Query("select p from Password p where p.idUser = :idUser and p.isDeleted = 0 and p.isExpired = 0")
	List<Password> getPasswordListById(@Param("idUser") Integer idUser);

	@Query("select p from Password p where p.idPassword = :idPassword and p.isDeleted = 0 and p.isExpired = 0")
	Password getPasswordById(@Param("idPassword") Integer idPassword);

	@Query("select p from Password p where p.idPassword = :idPassword and p.isExpired = 1")
	Password getPasswordExpiredObject(@Param("idPassword") Integer idPassword);
}