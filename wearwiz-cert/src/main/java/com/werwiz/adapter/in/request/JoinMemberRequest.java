package com.werwiz.adapter.in.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
//public class JoinMemberRequest extends SelfValidating {
public class JoinMemberRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2,max = 10)
    private String name;

    @Size(min = 4, max = 15)
    private String password;

    @Size(min = 4, max = 15)
    private String confirmPassword;

    @NotNull
    @Size(min = 2, max = 10)
    private String nickName;

    private String introduce;

    private String area;

    private List<License> licenses;

    private List<Category> categories;

    @Data
    @NoArgsConstructor @AllArgsConstructor
    public static class License{
        @NotNull
        private Long licenseId;

        @NotNull
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {
        @NotNull
        private Long id;

        @NotNull
        private String name;
    }
}


