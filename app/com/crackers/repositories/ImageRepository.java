package com.crackers.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Image;

public interface ImageRepository extends GraphRepository<Image>
{

    @Query("match (i:Image) where i.user.idUser = :idUser and i.isDeleted = 0 return i")
    Image getImageById(@Param("idUser") Integer idUser);

    @Query("match (i:Image) where i.user.idUser in (:idUser) and i.isDeleted = 0 return i")
    List<Image> getImages(@Param("idUser") List<Integer> idUser);

    @Query("match (ei:Image) where ei.idImage = :idImage and ei.isDeleted = 0 return ei")
    Image getImage(@Param("idImage") Integer idImage);

    @Query("match (r:Image) where r.isDeleted = 0 return r")
    List<Image> getImages();

    @Query("match (ie:Image) where ie.isDeleted = 0 return count(ie.idImage)")
    Long getImagesCount();

    @Query("match (ie:Image) where ie.isDeleted = :isDeleted return ie")
    List<Image> getImages(@Param("isDeleted") Short isDeleted, Pageable pageable);
}