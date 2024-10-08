package com.werwiz.adapter.out.persistence;

import com.wearwiz.common.PersistenceAdapter;
import com.werwiz.adapter.out.persistence.entity.ImageEntity;
import com.werwiz.adapter.out.persistence.entity.PortfolioEntity;
import com.werwiz.adapter.out.persistence.repository.ImageEntityRepository;
import com.werwiz.application.port.out.UploadImagePort;
import com.werwiz.domain.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class ImageAdapter implements UploadImagePort {

    private final ImageEntityRepository imageEntityRepository;
    @Override
    public ImageEntity uploadImage(Image image, PortfolioEntity portfolio) {
        ImageEntity imageEntity = ImageEntity.builder()
                .filePath(image.getFilePath())
                .build();

        portfolio.addImage(imageEntity);
        return imageEntityRepository.save(imageEntity);
    }
}
