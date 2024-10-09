package com.werwiz.application.port.out;

import com.werwiz.adapter.out.persistence.entity.CategoryEntity;

import java.util.List;

public interface FindCategoryPort {

    List<CategoryEntity> findAll();
}
