package com.werwiz.adapter.out.persistence.repository;

import com.werwiz.adapter.out.persistence.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageEntityRepository extends JpaRepository<ImageEntity, Long> {
}
