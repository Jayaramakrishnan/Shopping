package com.crackers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crackers.model.UserState;


public interface UserStateRepository extends JpaRepository<UserState, Integer>
{}