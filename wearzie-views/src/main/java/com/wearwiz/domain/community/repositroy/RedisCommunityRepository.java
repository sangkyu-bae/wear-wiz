package com.wearwiz.domain.community.repositroy;

import com.wearwiz.domain.community.RedisCommunityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RedisCommunityRepository extends CrudRepository<RedisCommunityEntity,String> {
    List<RedisCommunityEntity> findAllById(List<String> ids);
}
