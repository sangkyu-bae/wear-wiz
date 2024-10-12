package com.werwiz.application.port.out;

import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.domain.Category;
import com.werwiz.domain.License;
import com.werwiz.domain.Member;

public interface UpdateMemberPort {

    MemberEntity updateMember(Member updateMember);

    MemberEntity addCategoryToMember(long memberId, Category category);

    MemberEntity subtractCategoryToMember(long memberId, Category category);

    MemberEntity addLicenseToMember(long memberId, License license);
    MemberEntity subtractLicenseToMember(long memberId, License license);


}
