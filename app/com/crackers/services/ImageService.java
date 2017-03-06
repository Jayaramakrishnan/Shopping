package com.crackers.services;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crackers.dto.ImageDto;
import com.crackers.manager.db.ImageManager;
import com.crackers.model.Image;
import com.crackers.translators.ImageTranslator;

@Component
public class ImageService {

	@Resource
	private ImageManager	imageManager;
	@Resource
	private ImageTranslator	imageTranslator;

	public ImageDto getImageDto(Long idUser) throws InvocationTargetException {
		Image image = imageManager.getImage(idUser);
		return imageTranslator.translateImageToDto(image);
	}

	public ImageDto getImageId(Long idUser) throws InvocationTargetException {
		Image image = imageManager.getImage(idUser);
		return imageTranslator.translateImageToDtoById(image);
	}
}