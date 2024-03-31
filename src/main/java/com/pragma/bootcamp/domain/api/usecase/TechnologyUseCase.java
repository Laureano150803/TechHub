package com.pragma.bootcamp.domain.api.usecase;

import com.pragma.bootcamp.domain.api.ITechnologyServicePort;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.ITechnologyPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }
    @Override
    public void saveTechnology(Technology technology) {
        technologyPersistencePort.saveTechnology(technology);
    }
    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendant) {
        return technologyPersistencePort.getAllTechnologies(page, size, isAscendant);
    }
}
