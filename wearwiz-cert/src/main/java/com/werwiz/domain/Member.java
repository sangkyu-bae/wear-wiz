package com.werwiz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Member {

    private final Long memberId;

    private final String email;

    private final String area;

    private final Integer counsel;

    private final Double rate;

    private final String nickName;

    private final String introduce;

    private final List<Category> categorys;

    private final List<Role> roles;

    private final Portfolio portfolio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updateAt;

    private final String name;

    private final String password;

    public static Member createMember(
            Long memberId,
            String email,
            String area,
            Integer counsel,
            Double rate,
            String nickName,
            String introduce,
            List<Category> categorys,
            List<Role> roles,
            Portfolio portfolio,
            LocalDateTime createAt,
            LocalDateTime updateAt,
            String name,
            String password
    ){

        return new Member(memberId,
                email,
                area,
                counsel,
                rate,
                nickName,
                introduce,
                categorys,
                roles,
                portfolio,
                createAt,
                updateAt,
                name,
                password
        );
    }
}
