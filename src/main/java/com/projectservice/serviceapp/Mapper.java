package com.projectservice.serviceapp;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper<Entity, DTO> {

    public DTO mapToDto(Entity entity, Class<DTO> dtoClass) {
        return new ModelMapper().map(entity, dtoClass);
    }

    public Entity mapToEntity(DTO dto, Class<Entity> entityClass) {
        return new ModelMapper().map(dto, entityClass);
    }

    public List<DTO> mapToDtoList(List<Entity> entityList, Class<DTO> dtoClass) {
        return entityList.stream().map(entity -> mapToDto(entity, dtoClass)).collect(Collectors.toList());
    }

    public List<Entity> mapToEntityList(List<DTO> dtoList, Class<Entity> entityClass) {
        return dtoList.stream().map(dto -> mapToEntity(dto, entityClass)).collect(Collectors.toList());
    }
}
