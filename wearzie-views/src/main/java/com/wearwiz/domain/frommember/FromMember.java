package com.wearwiz.domain.frommember;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FromMember {

    private Long id;

    private long fromMemberId;

    public static FromMember createGenerate(
            Long id,
            long fromMemberId
    ){
        return new FromMember(id,fromMemberId);
    }
}
