package com.werwiz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Role {

    private Long id;

    private String roleName;

    public static Role createRole(Long id, String roleName){
        return new Role(id, roleName);

    }
}
