package com.werwiz.adapter.in.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CategoryRequest {
    @NotNull
    private Long id;

    @NotNull
    private String name;
}
