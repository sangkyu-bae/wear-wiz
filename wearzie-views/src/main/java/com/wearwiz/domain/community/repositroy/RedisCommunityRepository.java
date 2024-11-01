package com.wearwiz.domain.community.repositroy;

import com.wearwiz.domain.community.RedisCommunityEntity;
import org.springframework.data.repository.CrudRepository;

public interface RedisCommunityRepository extends CrudRepository<RedisCommunityEntity,String> {
}
