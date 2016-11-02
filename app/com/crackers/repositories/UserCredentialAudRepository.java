package com.crackers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.UserCredentialAud;

public interface UserCredentialAudRepository extends JpaRepository<UserCredentialAud, Integer>
{

	@Query("select p from UserCredentialAud p where p.idUser = :idUser and p.isDeleted = 0 order by p.updatedOn DESC")
	List<UserCredentialAud> verifyPreviousPasswords(@Param("idUser") Integer idUser);
}