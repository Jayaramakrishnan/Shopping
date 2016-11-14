package com.crackers.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.ImageColorCode;

public interface ImageColorCodeRepository extends GraphRepository<ImageColorCode>
{

    @Query("match (icc:ImageColorCode) where icc.idImageColorCode = :idImageColorCode and icc.isDeleted = 0 return icc.imageColorCode")
    String getImageColorCode(@Param("idImageColorCode") Integer idImageColorCode);

    @Query("match (icc:ImageColorCode) where icc.isDeleted = 0 return icc")
    List<ImageColorCode> getImageColorCodes();
}