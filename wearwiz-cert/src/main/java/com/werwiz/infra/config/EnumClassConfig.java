package com.werwiz.infra.config;

import com.werwiz.adapter.out.persistence.mapper.EnumClassMapper;
import com.werwiz.adapter.out.persistence.mapper.EnumClassMapperValue;
import com.werwiz.application.port.in.FindCategoryUseCase;
import com.werwiz.application.port.in.FindLicenseUseCase;
import com.werwiz.domain.Category;
import com.werwiz.domain.enums.ConsulEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class EnumClassConfig {

    private final FindCategoryUseCase findCategoryUseCase;

    private final FindLicenseUseCase findLicenseUseCase;
    @Bean
    public EnumClassMapper enumMapper(){
        EnumClassMapper enumClassMapper = new EnumClassMapper();
        List<EnumClassMapperValue> categoryEnum = findCategoryUseCase.findAll()
                        .stream().map(category -> new EnumClassMapperValue(category))
                        .collect(Collectors.toList());

        List<EnumClassMapperValue> licenseEnum = findLicenseUseCase.findAll()
                        .stream().map(license -> new EnumClassMapperValue(license))
                        .collect(Collectors.toList());

        enumClassMapper.put("license",licenseEnum);
        enumClassMapper.put("category",categoryEnum);
        enumClassMapper.put("consul", ConsulEnum.class);
        return enumClassMapper;
    }

}
