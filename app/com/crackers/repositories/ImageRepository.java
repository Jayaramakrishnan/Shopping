package com.crackers.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crackers.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>
{

	@Query("select i from Image i where i.user.idUser = :idUser and i.isDeleted = 0")
	Image getImageById(@Param("idUser") Integer idUser);

	@Query("select i from Image i where i.user.idUser in (:idUser) and i.isDeleted = 0")
	List<Image> getImages(@Param("idUser") List<Integer> idUser);

	@Query("select ei from Image ei where ei.idImage = :idImage and ei.isDeleted = 0")
	Image getImage(@Param("idImage") Integer idImage);

	@Query("select r from Image r where r.isDeleted = 0")
	List<Image> getImages();

	@Query("select count(ie.idImage) from Image ie where ie.isDeleted= 0")
	Long getImagesCount();

	@Query("select ie from Image ie where ie.isDeleted = :isDeleted")
	List<Image> getImages(@Param("isDeleted") Short isDeleted, Pageable pageable);
}