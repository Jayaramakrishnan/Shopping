package com.crackers.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.crackers.model.UserState;


public interface UserStateRepository extends GraphRepository<UserState>
{}