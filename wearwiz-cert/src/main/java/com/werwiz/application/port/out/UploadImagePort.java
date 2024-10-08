package com.werwiz.application.port.out;

import com.werwiz.adapter.out.persistence.entity.ImageEntity;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.adapter.out.persistence.entity.PortfolioEntity;
import com.werwiz.domain.Image;

public interface UploadImagePort {
    ImageEntity uploadImage(Image image, PortfolioEntity portfolioEntity);
}
