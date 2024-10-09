package com.werwiz.adapter.out.persistence;

import com.wearwiz.common.PersistenceAdapter;
import com.werwiz.adapter.out.persistence.entity.CategoryEntity;
import com.werwiz.adapter.out.persistence.repository.CategoryEntityRepository;
import com.werwiz.application.port.out.FindCategoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class CategoryAdapter implements FindCategoryPort {

    private final CategoryEntityRepository categoryEntityRepository;
    @Override
    public List<CategoryEntity> findAll() {
        return categoryEntityRepository.findAll();
    }
}
