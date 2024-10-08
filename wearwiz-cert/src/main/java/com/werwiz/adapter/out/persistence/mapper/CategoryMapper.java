package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.domain.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryMapper {

    public Category mapToDomain(JoinMemberRequest.Category category){
        return Category.createCategory(category.getId(), category.getName());
    }
}