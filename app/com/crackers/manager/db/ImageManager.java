package com.crackers.manager.db;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crackers.model.Image;
import com.crackers.repositories.ImageRepository;

@Component
public class ImageManager
{

    @Resource
    private ImageRepository imageRepository;

    public Image getImage(Integer idUser)
    {
        return imageRepository.getImageById(idUser);
    }
}