package com.wearwiz.domain.community.repositroy;

import com.wearwiz.domain.community.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityViewRepository extends JpaRepository<CommunityEntity, Long> {
}
