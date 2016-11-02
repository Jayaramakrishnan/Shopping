package com.crackers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crackers.model.UserSource;

public interface UserSourceRepository extends JpaRepository<UserSource, Integer>
{}