package com.crackers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crackers.model.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer>
{}