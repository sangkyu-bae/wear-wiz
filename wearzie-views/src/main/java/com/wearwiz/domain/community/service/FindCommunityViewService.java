package com.wearwiz.domain.community.service;

import com.wearwiz.adapter.mapper.RedisCommunityMapper;
import com.wearwiz.adapter.service.RedisSortedService;
import com.wearwiz.domain.community.RedisCommunity;
import com.wearwiz.domain.community.RedisCommunityEntity;
import com.wearwiz.domain.community.repositroy.RedisCommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindCommunityViewService {

    private final RedisCommunityRepository redisCommunityRepository;

    private final RedisSortedService redisSortedService;

    private final RedisCommunityMapper redisCommunityMapper;


    public List<RedisCommunity> findCommunityByLimitAndKey(int limit, String key){
        List<String> rankKeys = redisSortedService.findByPaging(limit,key);
        List<RedisCommunityEntity> redisCommunityEntityList = redisCommunityRepository.findAllById(rankKeys);


        return redisCommunityEntityList.stream()
                .map(community-> redisCommunityMapper.mapToDomain(community))
                .collect(Collectors.toList());
    }
}
