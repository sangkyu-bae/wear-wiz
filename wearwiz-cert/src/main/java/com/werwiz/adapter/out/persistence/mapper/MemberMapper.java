package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.domain.Category;
import com.werwiz.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member mapToDomain(MemberEntity memberEntity){
       return  Member.createMember(
                memberEntity.getMemberId(),
                memberEntity.getEmail(),
                memberEntity.getArea(),
                memberEntity.getCounsel(),
                memberEntity.getRate(),
                memberEntity.getNickName(),
                memberEntity.getIntroduce(),
                null,
                null,
                null,
                memberEntity.getCreateAt(),
                memberEntity.getUpdateAt(),
                memberEntity.getName(),
                memberEntity.getPassword()
        );
    }


}