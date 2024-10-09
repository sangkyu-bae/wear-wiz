package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.domain.EnumClassType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumClassMapper {

    private Map<String, List<EnumClassMapperValue>>factory = new LinkedHashMap<>();

    public void put(String key, List<EnumClassMapperValue> value){
        factory.put(key, value);
    }

    public List<EnumClassMapperValue> get(String key){

        if(!factory.containsKey(key)){

        }

        return factory.get(key);
    }

    public Map<String,List<EnumClassMapperValue>> get(List<String> keys){
        if(keys == null || keys.size() == 0){
            return new LinkedHashMap<>();
        }

        return keys.stream()
                .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
    }


}
