package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Technology;

import java.util.List;

public interface  ITechnologyServicePort {
    void saveTechnology(Technology technology);
    List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendant);
}
