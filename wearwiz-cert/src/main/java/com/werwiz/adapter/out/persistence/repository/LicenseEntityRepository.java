package com.werwiz.adapter.out.persistence.repository;

import com.werwiz.adapter.out.persistence.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseEntityRepository extends JpaRepository<LicenseEntity,Long> {
}
