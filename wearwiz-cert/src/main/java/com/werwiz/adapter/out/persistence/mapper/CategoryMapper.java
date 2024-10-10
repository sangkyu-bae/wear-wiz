package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.adapter.in.request.CategoryRequest;
import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.adapter.out.persistence.entity.CategoryEntity;
import com.werwiz.domain.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryMapper {

    public Category mapToDomain(CategoryRequest category){
        return Category.createCategory(category.getId(), category.getName());
    }

    public Category mapToDomain(CategoryEntity categoryEntity){
        return Category.createCategory(categoryEntity.getId(),categoryEntity.getName());
    }
}