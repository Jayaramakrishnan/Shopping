package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Role;

public interface RoleRepository extends GraphRepository<Role>
{

	@Query("match (user:User)-[:HAS_A_ROLE]->(role)-[:HAS_ROLE_FUNCTIONAL_ACCESS]->(roleFunctionalAccess) "
			+ "where user.idUser = {idUser} and user.isDeleted = 0 and roleFunctionalAccess.isDeleted = 0 and "
			+ "user.idUserState = 1 return user, role, roleFunctionalAccess.idFunctionalResource, roleFunctionalAccess.idFunctionalAccess")
	List<Object[]> getUserResource(@Param("idUser") Long idUser);
	
	@Query("match (user:User)-[:HAS_A_ROLE]->(role) where user.idUser = {idUser} and user.isDeleted = 0 return role")
	Role getUserRole(@Param("idUser") Long idUser);
}