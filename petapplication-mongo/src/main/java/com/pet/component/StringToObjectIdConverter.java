package com.pet.component;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToObjectIdConverter implements Converter<String, ObjectId> {

    @Override
    public ObjectId convert(String source) {
        return new ObjectId(source);
    }
}
