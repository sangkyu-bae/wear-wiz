package com.werwiz.adapter.in.web;

import com.wearwiz.common.WebAdapter;
import com.werwiz.adapter.out.persistence.mapper.EnumClassMapper;
import com.werwiz.adapter.out.persistence.mapper.EnumClassMapperValue;
import com.werwiz.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@WebAdapter
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final EnumClassMapper enumClassMapper;

    @GetMapping("/member/category")
    public ResponseEntity<List<EnumClassMapperValue>> findCategory(){
        return ResponseEntity.ok().body(enumClassMapper.get("category"));
    }

}
