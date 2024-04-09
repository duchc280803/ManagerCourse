package com.example.managercourse.util;

import org.modelmapper.ModelMapper;

import java.util.List;

public class MapperUtil {

    private MapperUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static <T, R> List<R> toDTOs(List<T> entities, Class<R> dtoClass) {
        return entities
                .stream()
                .map(entity -> MODEL_MAPPER.map(entity, dtoClass))
                .toList();
    }

    public static <T, R> R toDTO(T entity, Class<R> dtoClass) {
        return MODEL_MAPPER.map(entity, dtoClass);
    }

}
