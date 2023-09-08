package com.offsidegaming.monitoringdemo.util;

import com.offsidegaming.monitoringdemo.domain.Measurement.Type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        if (type == null) {
            return null;
        }

        return type.getCode();
    }

    @Override
    public Type convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Type.values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
