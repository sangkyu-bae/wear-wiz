package com.werwiz.adapter.out.persistence.repository;

import com.werwiz.adapter.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity,Long> {
}
