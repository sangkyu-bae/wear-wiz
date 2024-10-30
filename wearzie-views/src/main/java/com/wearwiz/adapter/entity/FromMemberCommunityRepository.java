package com.wearwiz.adapter.entity;

import com.wearwiz.domain.community.CommunityEntity;
import com.wearwiz.domain.frommember.FromMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FromMemberCommunityRepository extends JpaRepository<FromMemberCommunityEntity,Long> {

    Optional<FromMemberCommunityEntity> findByCommunityAndViewFromMember(CommunityEntity communityEntity, FromMemberEntity fromMemberEntity);
}
