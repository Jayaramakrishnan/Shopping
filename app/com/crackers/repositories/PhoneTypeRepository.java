package com.crackers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crackers.model.PhoneType;

public interface PhoneTypeRepository extends JpaRepository<PhoneType, Integer>
{}