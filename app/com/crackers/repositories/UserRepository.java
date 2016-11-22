package com.crackers.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.User;

public interface UserRepository extends GraphRepository<User>
{

    @Query("match (u:User) where u.idUser = {idUser} and u.isDeleted = 0 return u")
    User validateById(@Param("idUser") Integer idUser);

    @Query("match (u:User) where LOWER(u.userName) = LOWER({userName}) and u.idUserState = 1 and u.isDeleted = 0 return u")
    User validateByUserName(@Param("userName") String userName);

    @Query("match (u:User) where LOWER(u.userName) = LOWER({userName}) and u.isDeleted = 0 return count(u.idUser)")
    Long getUser(@Param("userName") String userName);

    @Query("match (u:User) where u.idUserState = {idUserState} and u.isDeleted = 0 return u order by u.name ASC")
    List<User> getUserListName(@Param("idUserState") Integer idUserState, Pageable pageable);

    @Query("match (u:User) where u.idUserState = {idUserState} and u.isDeleted = 0 return count(u.idUser)")
    Long getUserCount(@Param("idUserState") Integer idUserState);

    @Query("match (u:User) where u.idUser = {idUser} and u.idUserState = 1 and u.isDeleted = 0 return u")
    User checkValidUser(@Param("idUser") Integer idUser);

    @Query("match (u:User) where u.idUserState = {idUserState} and u.idUser in [{idUser}] and u.isDeleted = 0 and (LOWER(u.name) =~ LOWER(('.*' + {keyword} + '.*')) or LOWER(u.userName) =~ LOWER(('.*' + {keyword} + '.*'))) return u order by u.name ASC")
    List<User> getAssigneeUserListName(@Param("idUserState") Integer idUserState, @Param("idUser") List<Integer> idUser, Pageable pageable, @Param("keyword") String keyword);

    @Query("match (u:User) where u.idUserState = {idUserState} and u.isDeleted = 0 and (LOWER(u.name) =~ LOWER(('.*' + {keyword} + '.*')) or LOWER(u.userName) =~ LOWER(('.*' + {keyword} + '.*'))) return u order by u.name ASC")
    List<User> getAssigneeUserListNameForGeneral(@Param("idUserState") Integer idUserState, Pageable pageable, @Param("keyword") String keyword);

    @Query("match (ie:User) where ie.isDeleted = 0 return count(ie.idUser)")
    Long getUsersCount();

    @Query("match (ie:User) where ie.isDeleted = {isDeleted} return ie")
    List<User> getUsers(@Param("isDeleted") Short isDeleted, Pageable pageable);

    @Query("match (u:User) where u.idUser = {idUser} return u.name")
    String getUserName(@Param("idUser") Integer idUser);
}