package com.werwiz.adapter.in.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMemberRequest {

    @NotNull
    @Email
    private String loginEmail;

    @NotNull
    @Size(min = 2, max = 15)
    private String password;
}
