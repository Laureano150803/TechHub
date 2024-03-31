package com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {
    @Mapping(target = "capabilities.name", ignore = true)
    @Mapping(target = "capabilities.description", ignore = true)
    Bootcamp toModel(BootcampEntity bootcampEntity);
    BootcampEntity toEntity(Bootcamp bootcamp);
}
