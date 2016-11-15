package com.crackers.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Role;
import com.crackers.model.User;
import com.crackers.model.UserRole;

public interface UserRoleRepository extends GraphRepository<UserRole>
{

    @Query("select ur.user, ur.role, rfa.functionalResource.idFunctionalResource, rfa.functionalAccess.idFunctionalAccess from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where ur.user.idUser = :idUser and ur.isDeleted = 0 and rfa.isDeleted = 0 and ur.user.isDeleted = 0 and ur.user.idUserState =1")
    List<Object[]> getUserResource(@Param("idUser") Integer idUser);

    @Query("select ur.user.idUser, ur.role.idRole, rfa.functionalResource.idFunctionalResource, rfa.functionalAccess.idFunctionalAccess from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where ur.user.idUser = :idUser and ur.isDeleted = 0 and rfa.isDeleted = 0 and ur.user.isDeleted = 0 and ur.user.idUserState =1")
    List<Object[]> getUserResourceAccessByUserId(@Param("idUser") Integer idUser);

    @Query("select ur.user.idUser,ur.role.idRole, rfa.functionalResource.idFunctionalResource, rfa.functionalAccess.idFunctionalAccess from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where rfa.functionalResource.idFunctionalResource = :idFunctionalResource and rfa.functionalAccess.idFunctionalAccess= :idFunctionalAccess and ur.isDeleted = 0 and rfa.isDeleted = 0 and ur.user.isDeleted = 0 and ur.user.idUserState =1")
    List<Object[]> getUserResourceAccess(@Param("idFunctionalResource") Integer resource, @Param("idFunctionalAccess") Integer integer);

    @Query("select ur.role from UserRole ur where ur.user.idUser = :idUser and ur.isDeleted = 0")
    Role getUserRole(@Param("idUser") Integer idUser);

    @Query("select ur.idFunctionalResource.idFunctionalResource,ur.idFunctionalAccess.idFunctionalAccess from UserFunctionalResourceAccess ur  where ur.idUser.idUser = :idUser and ur.idUser.isDeleted = 0 and ur.idUser.idUserState =1")
    List<Object[]> getUserFunction(@Param("idUser") Integer idUser);

    @Query("select count(cri) from CaseResourceInvestigator cri where cri.idUser.idUser = :idUser and cri.cases.status.idStatus != :idStatus and cri.isDeleted = 0")
    public Long getIndividualCaseInvestigator(@Param("idUser") Integer idUser, @Param("idStatus") Integer idStatus);

    // Need to UnComment for Information
    @Query("select count(cri) from InformationResourceInvestigator cri where cri.user.idUser = :idUser and cri.information.status.idStatus != :idStatus and cri.isDeleted = 0 ")
    public Long getIndividualInformationInvestigator(@Param("idUser") Integer idUser, @Param("idStatus") Integer idStatus);

    @Query("select ur.user from UserRole ur where ur.role.idRole= :manager and ur.isDeleted= 0")
    List<User> getManagers(@Param("manager") Integer accessConstants);

    @Query("select ur.user.idUser, rfa.functionalResource.idFunctionalResource, rfa.functionalAccess.idFunctionalAccess from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where rfa.functionalResource.idFunctionalResource = :idFunctionalResource and ur.isDeleted = 0 and rfa.isDeleted = 0 and ur.user.isDeleted = 0 and ur.user.idUserState =1")
    List<Object[]> getUserResourceAccess(@Param("idFunctionalResource") Integer integer);

    @Query("select ur.user.idUser,ur.role.idRole, rfa.functionalResource.idFunctionalResource, rfa.functionalAccess.idFunctionalAccess from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa inner join ur.user u inner join u.userClub uc where rfa.functionalResource.idFunctionalResource = :idFunctionalResource  and rfa.functionalAccess.idFunctionalAccess= :idFunctionalAccess and uc.idClub  in (:idClub) and uc.isDeleted = 0  and ur.isDeleted= 0")
    List<Object[]> getUserFilteredByClub(@Param("idFunctionalResource") Integer integer, @Param("idFunctionalAccess") Integer access, @Param("idClub") List<Integer> idClub);

    @Query("select ur from UserRole ur where  ur.user.idUser = :idUser")
    UserRole getUserRoles(@Param("idUser") Integer idUser);

    @Query("select r.role from UserRole r where r.user.idUser = :idUser and r.isDeleted = 0")
    public Role getRoleByIdUser(@Param("idUser") Integer idUser);

    @Query("select ur.user.idUser,ur.role.idRole, rfa.functionalResource.idFunctionalResource, rfa.functionalAccess.idFunctionalAccess from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where rfa.functionalResource.idFunctionalResource = :idFunctionalResource  and rfa.functionalAccess.idFunctionalAccess= :idFunctionalAccess and ur.isDeleted= 0")
    List<Object[]> getUserFilteredByClubForIncident(@Param("idFunctionalResource") Integer integer, @Param("idFunctionalAccess") Integer access);

    @Query("select ur.user.idUser,ur.role.idRole, rfa.functionalResource.idFunctionalResource, rfa.functionalAccess.idFunctionalAccess from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where rfa.functionalResource.idFunctionalResource = :idFunctionalResource  and rfa.functionalAccess.idFunctionalAccess= :idFunctionalAccess and ur.user.idUser = :idUser and ur.isDeleted= 0")
    List<Object[]> getMyclubAccessForUser(@Param("idFunctionalResource") Integer integer, @Param("idFunctionalAccess") Integer access, @Param("idUser") Integer idUser);

    @Query("select ur.user.idUser from UserRole ur where ur.role.idRole in (:idRole) and ur.isDeleted = 0")
    List<Integer> getUserIdsBasedonRole(@Param("idRole") List<Integer> idRole);

    @Query("select ur.role.idRole,ur.role.role from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where rfa.functionalResource.idFunctionalResource = :idFunctionalResource and ur.isDeleted = 0 and rfa.isDeleted = 0")
    List<Object[]> getRoleResourceAccess(@Param("idFunctionalResource") Integer integer);

    @Query("select ur.user.idUser from UserRole ur inner join ur.role r inner join r.roleFunctionalAccess rfa where rfa.functionalResource.idFunctionalResource = :idFunctionalResource  and rfa.functionalAccess.idFunctionalAccess= :idFunctionalAccess and ur.user.idUser = :idUser and ur.isDeleted= 0 and ur.user.isDeleted = 0 and ur.user.idUserState =1")
    List<Integer> getUserMyResourceAccess(@Param("idFunctionalResource") Integer integer, @Param("idFunctionalAccess") Integer access, @Param("idUser") Integer idUser);

    @Query("select ur.user.idUser from UserRole ur inner join ur.user u inner join u.userClub uc  where ur.role.idRole in (:idRole) and uc.idClub in (:idClub) and u.idUserState = 1 and uc.isDeleted = 0 and ur.isDeleted = 0")
    List<Integer> getUserByClubAndRoles(@Param("idRole") List<Integer> arrayList, @Param("idClub") Integer idClub);

    @Query("select u.idUser from User u inner join u.userClub uc  where u.idUser in (:idUser) and uc.idClub in (:idClub) and u.idUserState = 1 and uc.isDeleted = 0 and u.isDeleted = 0")
    List<Integer> getUserByClubAndUserIds(@Param("idUser") List<Integer> arrayList, @Param("idClub") Integer idClub);

    @Query("select count(ie.idUserRole) from UserRole ie where ie.isDeleted= 0 and ie.user.isDeleted = 0 and ie.role.isDeleted = 0")
    Long getUserRoleCount();

    @Query("select ie from UserRole ie where ie.isDeleted = :isDeleted and ie.user.isDeleted = :isDeleted and ie.role.isDeleted = :isDeleted")
    List<UserRole> getUserRole(@Param("isDeleted") Short isDeleted, Pageable pageable);
}