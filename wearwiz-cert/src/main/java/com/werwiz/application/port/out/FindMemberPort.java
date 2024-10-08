package com.werwiz.application.port.out;

import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.domain.Member;

public interface FindMemberPort {
    MemberEntity findById(long id);
}
