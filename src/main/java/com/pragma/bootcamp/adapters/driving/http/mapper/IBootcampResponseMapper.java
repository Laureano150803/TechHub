package com.pragma.bootcamp.adapters.driving.http.mapper;

import com.pragma.bootcamp.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBootcampResponseMapper {
    BootcampResponse toBootcampResponse(Bootcamp bootcamp);
}
