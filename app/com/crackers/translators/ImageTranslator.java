package com.crackers.translators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.crackers.common.BeanUtil;
import com.crackers.dto.ImageDto;
import com.crackers.model.Image;

@Component
public class ImageTranslator
{

    public List<ImageDto> translateToImageDto(List<Image> image)
    {
        List<ImageDto> imageDtos = new ArrayList<>();
        List<ImageDto> imageDtosFinal = new ArrayList<>();
        Iterator<Image> imageIterator = image.iterator();
        while (imageIterator.hasNext())
        {
            Image imag = imageIterator.next();
            ImageDto imageDto = new ImageDto();
            BeanUtils.copyProperties(imag, imageDto);
            imageDtos.add(imageDto);
        }
        imageDtosFinal.addAll(imageDtos);
        return imageDtosFinal;
    }

    public Image translateDtoToImage(ImageDto imageDto) throws InvocationTargetException
    {
        Image image = new Image();
        if (imageDto == null)
        {
            return image;
        }
        BeanUtil.copyBeanProperties(imageDto, image, new ArrayList<>());
        return image;
    }

    public ImageDto translateImageToDto(Image image) throws InvocationTargetException
    {
        ImageDto imageDto = new ImageDto();
        if (image == null)
        {
            return imageDto;
        }
        BeanUtil.copyBeanProperties(image, imageDto, new ArrayList<>());
        return imageDto;
    }

    public ImageDto translateImageToDtoById(Image image) throws InvocationTargetException
    {
        ImageDto imageDto = new ImageDto();
        if (image == null)
        {
            return imageDto;
        }
        imageDto.setIdImage(image.getId());
        return imageDto;
    }
}