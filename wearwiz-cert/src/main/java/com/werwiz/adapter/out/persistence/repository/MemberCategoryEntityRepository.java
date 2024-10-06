package com.werwiz.adapter.out.persistence.repository;

import com.werwiz.adapter.out.persistence.entity.MemberCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCategoryEntityRepository extends JpaRepository<MemberCategoryEntity,Long> {
}
