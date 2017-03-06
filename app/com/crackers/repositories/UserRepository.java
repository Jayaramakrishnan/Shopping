package com.crackers.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.User;

public interface UserRepository extends GraphRepository<User> {

	@Query("match (u:User) where u.id = {idUser} and u.isDeleted = 0 return u")
	User validateById(@Param("idUser") Long idUser);

	@Query("match (u:User) where LOWER(u.userName) = LOWER({userName}) and u.isDeleted = 0 return u")
	User validateByUserName(@Param("userName") String userName);

	@Query("match (u:User) where LOWER(u.userName) = LOWER({userName}) and u.isDeleted = 0 return count(u.id)")
	Long getUser(@Param("userName") String userName);

	@Query("match (u:User) where u.id = {idUser} and u.isDeleted = 0 return u")
	User checkValidUser(@Param("idUser") Long idUser);
}