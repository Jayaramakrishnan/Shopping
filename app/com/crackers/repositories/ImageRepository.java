package com.crackers.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Image;

public interface ImageRepository extends GraphRepository<Image> {

	@Query("match (image)<-[:HAS_AN_IMAGE]-(user:User) where user.id = {idUser} and image.isDeleted = 0 return image")
	Image getImageById(@Param("idUser") Long idUser);

	@Query("match (image)<-[:HAS_AN_IMAGE]-(user:User) where user.id = {idUser} and image.isDeleted = 0 return image")
	List<Image> getImages(@Param("idUser") List<Long> idUser);

	@Query("match (ei:Image) where ei.id = {idImage} and ei.isDeleted = 0 return ei")
	Image getImage(@Param("idImage") Integer idImage);

	@Query("match (r:Image) where r.isDeleted = 0 return r")
	List<Image> getImages();

	@Query("match (ie:Image) where ie.isDeleted = 0 return count(ie.id)")
	Long getImagesCount();

	@Query("match (ie:Image) where ie.isDeleted = {isDeleted} return ie")
	List<Image> getImages(@Param("isDeleted") Short isDeleted, Pageable pageable);
}