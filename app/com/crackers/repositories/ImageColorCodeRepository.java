package com.crackers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.ImageColorCode;

public interface ImageColorCodeRepository extends JpaRepository<ImageColorCode, Integer>
{

	@Query("select icc.imageColorCode from ImageColorCode icc where icc.idImageColorCode = :idImageColorCode and icc.isDeleted=0")
	String getImageColorCode(@Param("idImageColorCode") Integer idImageColorCode);

	@Query("select icc from ImageColorCode icc where icc.isDeleted=0")
	List<ImageColorCode> getImageColorCodes();
}