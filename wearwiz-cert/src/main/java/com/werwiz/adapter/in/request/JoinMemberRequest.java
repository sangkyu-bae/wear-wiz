package com.werwiz.adapter.in.request;

import com.wearwiz.common.SelfValidating;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinMemberRequest extends SelfValidating {

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


