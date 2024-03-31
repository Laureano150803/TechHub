package com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapabilityEntityMapper {
    @Mapping(target = "technologies.name", ignore = true)
    @Mapping(target = "technologies.description", ignore = true)
    Capability toModel(CapabilityEntity capabilityEntity);
    CapabilityEntity toEntity(Capability capability);
}
