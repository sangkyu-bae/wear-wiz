package com.werwiz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginMember {

    private String accessToken;

    private String refreshToken;

    private String nickName;

    public static LoginMember createGenerateMember(String accessToken,String refreshToken,String nickName){
        return new LoginMember(accessToken,refreshToken,nickName);
    }

}
