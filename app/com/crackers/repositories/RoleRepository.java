package com.crackers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crackers.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{}