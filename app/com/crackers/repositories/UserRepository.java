package com.crackers.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.User;


public interface UserRepository extends JpaRepository<User, Integer>
{

	@Query("select u from User u where u.idUser = :idUser and u.isDeleted =0")
	User validateById(@Param("idUser") Integer idUser);

	@Query("select u from User u where LOWER(u.userName )= LOWER(:userName) and u.idUserState = 1 and u.isDeleted = 0")
	User validateByUserName(@Param("userName") String userName);

	@Query("select count(u) from User u where LOWER(u.userName) = LOWER(:userName) and u.isDeleted =0")
	Long getUser(@Param("userName") String userName);

	@Query("select u from User u where u.idUserState = :idUserState and u.isDeleted =0 order by u.firstName ASC")
	List<User> getUserListFirstName(@Param("idUserState") Integer idUserState, Pageable pageable);

	@Query("select u from User u where u.idUserState = :idUserState and u.isDeleted =0 order by u.lastName ASC")
	List<User> getUserListLastName(@Param("idUserState") Integer idUserState, Pageable pageable);

	@Query("select count(u) from User u where u.idUserState = :idUserState and u.isDeleted =0")
	Long getUserCount(@Param("idUserState") Integer idUserState);

	@Query("select u from User u where u.idUser = :idUser and u.idUserState = 1 and u.isDeleted =0")
	User checkValidUser(@Param("idUser") Integer idUser);

	@Query("select u from User u where u.idSource = :source and  u.isDeleted =0")
	List<User> getAdLdapUsers(@Param("source") Integer source);

	@Query("select u from User u where u.idUserState = :idUserState and u.idUser in :idUser and u.isDeleted =0 and (LOWER(u.fullName) like CONCAT('%',:keyword,'%') OR LOWER(u.firstName) like CONCAT('%',:keyword,'%') or LOWER(u.lastName) like CONCAT('%',:keyword,'%') or LOWER(u.userName) like CONCAT('%',:keyword,'%')) order by u.firstName ASC")
	List<User> getAssigneeUserListFirstName(@Param("idUserState") Integer idUserState,@Param("idUser") List<Integer> idUser, Pageable pageable, @Param("keyword") String keyword);

	@Query("select u from User u where u.idUserState = :idUserState and u.idUser in :idUser and u.isDeleted =0 and (LOWER(u.fullName) like CONCAT('%',:keyword,'%') OR LOWER(u.firstName) like CONCAT('%',:keyword,'%') or LOWER(u.lastName) like CONCAT('%',:keyword,'%') or LOWER(u.userName) like CONCAT('%',:keyword,'%')) order by u.lastName ASC")
	List<User> getAssigneeUserListLastName(@Param("idUserState") Integer idUserState ,@Param("idUser") List<Integer> idUser, Pageable pageable, @Param("keyword") String keyword);
	
	@Query("select u from User u where u.idUserState = :idUserState  and u.isDeleted =0 and (LOWER(u.fullName) like CONCAT('%',:keyword,'%') OR LOWER(u.firstName) like CONCAT('%',:keyword,'%') or LOWER(u.lastName) like CONCAT('%',:keyword,'%') or LOWER(u.userName) like CONCAT('%',:keyword,'%')) order by u.firstName ASC")
	List<User> getAssigneeUserListFirstNameForGeneral(@Param("idUserState") Integer idUserState , Pageable pageable, @Param("keyword") String keyword);

	@Query("select u from User u where u.idUserState = :idUserState  and u.isDeleted =0 and (LOWER(u.fullName) like CONCAT('%',:keyword,'%') OR LOWER(u.firstName) like CONCAT('%',:keyword,'%') or LOWER(u.lastName) like CONCAT('%',:keyword,'%') or LOWER(u.userName) like CONCAT('%',:keyword,'%')) order by u.lastName ASC")
	List<User> getAssigneeUserListLastNameForGeneral(@Param("idUserState") Integer idUserState , Pageable pageable, @Param("keyword") String keyword);
	
	@Query("select count(ie.idUser) from User ie where ie.isDeleted= 0")
	Long getUsersCount();
	
	@Query("select ie from User ie where ie.isDeleted = :isDeleted")
	List<User> getUsers(@Param("isDeleted") Short isDeleted, Pageable pageable);
	
	@Query("select u.fullName from User u where u.idUser = :idUser")
	String getUserName(@Param("idUser") Integer createdBy);
}