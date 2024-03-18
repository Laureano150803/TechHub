package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Technology;

import java.util.List;

public interface  ITechnologyServicePort {
    void saveTechnology(Technology technology);
    Technology getTechnology(String name);
    List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendent);
    Technology updateTechnology(Technology technology);
    void deleteTechnology(Long id);
}
