package com.pragma.bootcamp.domain.spi;

import com.pragma.bootcamp.domain.model.Technology;

import java.util.List;

public interface ITechnologyPersistancePort {
    void saveTechnology(Technology technology);
    Technology getTechnology(String name);
    List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendent);
    Technology updateTechnology(Technology technology);
    void deleteTechnology(Long id);

}
