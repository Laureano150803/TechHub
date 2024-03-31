package com.pragma.bootcamp.domain.spi;

import com.pragma.bootcamp.domain.model.Technology;

import java.util.List;

public interface ITechnologyPersistencePort {
    void saveTechnology(Technology technology);
    List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendant);
}
