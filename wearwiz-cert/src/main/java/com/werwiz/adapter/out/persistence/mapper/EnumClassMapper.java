package com.werwiz.adapter.out.persistence.mapper;

import com.wearwiz.common.EnumMapperType;
import com.werwiz.domain.EnumClassType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumClassMapper {

    private Map<String, List<EnumClassMapperValue>>factory = new LinkedHashMap<>();

    public void put(String key, List<EnumClassMapperValue> value){
        factory.put(key, value);
    }

    public void put(String key, Class<? extends EnumMapperType> e){
        factory.put(key,toEnumValues(e));
    }

    private List<EnumClassMapperValue> toEnumValues(Class <? extends EnumMapperType>e){
        return Arrays.stream(e.getEnumConstants())
                .map(EnumClassMapperValue::new)
                .collect(Collectors.toList());
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
