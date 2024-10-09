package com.werwiz.application.port.in;

import com.werwiz.domain.Category;

import java.util.List;

public interface FindCategoryUseCase {
    List<Category> findAll();
}
