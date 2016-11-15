package com.crackers.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.crackers.model.UserSource;

public interface UserSourceRepository extends GraphRepository<UserSource>
{}