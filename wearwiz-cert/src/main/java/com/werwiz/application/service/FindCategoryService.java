package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.werwiz.adapter.in.request.LoginMemberRequest;
import com.werwiz.adapter.out.persistence.entity.CategoryEntity;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.adapter.out.persistence.mapper.CategoryMapper;
import com.werwiz.application.port.in.FindCategoryUseCase;
import com.werwiz.application.port.out.FindCategoryPort;
import com.werwiz.domain.Category;
import com.werwiz.domain.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class FindCategoryService implements FindCategoryUseCase {

    private final FindCategoryPort findCategoryPort;

    private final CategoryMapper categoryMapper;
    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {

        List<CategoryEntity> categoryEntityList = findCategoryPort.findAll();
        return categoryEntityList.stream()
                .map(categoryEntity -> categoryMapper.mapToDomain(categoryEntity))
                .collect(Collectors.toList());
    }


}
