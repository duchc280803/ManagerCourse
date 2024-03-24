package com.example.managercourse.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {

    public MapperUtil() {

    }

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static <T, R> List<R> toDTOs(List<T> entities, Class<R> dtoClass) {
        return entities
                .stream()
                .map(entity -> MODEL_MAPPER.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public static <T, R> List<R> ToEntities(List<T> requestDTOs, Class<R> entityClass) {
        return requestDTOs
                .stream()
                .map(entity -> MODEL_MAPPER.map(entity, entityClass))
                .collect(Collectors.toList());
    }

    public static <T, R> R toDTO(T entity, Class<R> dtoClass) {
        return MODEL_MAPPER.map(entity, dtoClass);
    }

    public static <T, R> R ToEntity(T requestDTO, Class<R> entityClass) {
        return MODEL_MAPPER.map(requestDTO, entityClass);
    }
}
