package com.werwiz.adapter.out.persistence;

import com.wearwiz.common.PersistenceAdapter;
import com.werwiz.adapter.out.persistence.entity.ImageEntity;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.adapter.out.persistence.entity.PortfolioEntity;
import com.werwiz.adapter.out.persistence.repository.ImageEntityRepository;
import com.werwiz.adapter.out.persistence.repository.PortfolioEntityRepository;
import com.werwiz.application.port.out.UploadImagePort;
import com.werwiz.domain.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class ImageAdapter implements UploadImagePort {

    private final ImageEntityRepository imageEntityRepository;
    private final PortfolioEntityRepository portfolioEntityRepository;
    @Override
    public ImageEntity uploadImage(Image image, MemberEntity memberEntity) {
        ImageEntity imageEntity = ImageEntity.builder()
                .filePath(image.getFilePath())
                .build();
        PortfolioEntity portfolio = memberEntity.getPortfolio();
        if(portfolio == null){
            portfolio = PortfolioEntity.builder()
                    .build();

            portfolio = portfolioEntityRepository.save(portfolio);
        }


        portfolio.addImage(imageEntity);
        memberEntity.setPortfolio(portfolio);

        return imageEntityRepository.save(imageEntity);
    }
}
