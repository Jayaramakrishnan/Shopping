package com.crackers.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.crackers.model.Role;

public interface RoleRepository extends GraphRepository<Role>
{}