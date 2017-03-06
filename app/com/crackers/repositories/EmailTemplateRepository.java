package com.crackers.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.crackers.model.EmailTemplate;

public interface EmailTemplateRepository extends GraphRepository<EmailTemplate> {}