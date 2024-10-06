package com.werwiz.adapter.out.persistence.repository;

import com.werwiz.adapter.out.persistence.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioEntityRepository extends JpaRepository<PortfolioEntity,Long> {
}
