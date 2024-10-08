package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.adapter.out.persistence.entity.ImageEntity;
import com.werwiz.domain.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ImageMapper {

    public Image mapToDomain(ImageEntity entity){
        return Image.createImage(entity.getId(),entity.getFilePath());
    }
}
