package com.crackers.repositories;

import java.util.List;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Email;

public interface EmailRepository extends JpaRepository<Email, Integer>
{

	@Query("select e from Email e where e.user.idUser=:createdBy and e.isPrimary= 1 and e.isDeleted = 0")
	Email getcreatedByMailId(@Param("createdBy") Integer createdBy);

	@Query("select e from Email e join e.user u where e.isDeleted = 0 and u.idUser in (select r.idUser.idUser from CaseResourceInvestigator r join r.cases c where c.idCase= :idCase)")
	TreeSet<Email> getinvestigatorsMailId(@Param("idCase") Integer idCase);

	@Query("select e from Email e join e.user u where u.idUser = :idUser and e.isDeleted = 0")
	List<Email> getUsersMailId(@Param("idUser") Integer idUser);
	
	@Query("select e from Email e join e.user u where u.idUser = :idUser and e.isPrimary= 1 and e.isDeleted = 0")
	Email getPrimaryMail(@Param("idUser") Integer idUser);
	
	@Query("select e.user.idUser from Email e where LOWER(e.email) like LOWER(:email) and e.user.idUserState=1 and e.user.isDeleted=0 and e.isDeleted=0")
	Integer validateNewUserMailId(@Param("email") String email);
	
	@Query("select e.email from Email e join e.user u where u.idUserState=1 and u.idUser = :idUser and e.isPrimary= 1 and u.isDeleted=0 and e.isDeleted = 0")
	String getEmailByIdUser(@Param("idUser") Integer idUser);
	
	@Query("select e from Email e where e.isDeleted = 0")
	List<Email> getEMails();
}
