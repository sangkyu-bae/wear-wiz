package com.werwiz.adapter.in.web;

import com.wearwiz.common.WebAdapter;
import com.werwiz.adapter.out.persistence.mapper.EnumClassMapper;
import com.werwiz.adapter.out.persistence.mapper.EnumClassMapperValue;
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
public class ConsulController {

    private final EnumClassMapper enumClassMapper;

    @GetMapping("/member/consul")
    public ResponseEntity<List<EnumClassMapperValue>> findConsul(){
        return ResponseEntity.ok().body(enumClassMapper.get("consul"));
    }
}
