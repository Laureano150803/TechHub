package com.pragma.bootcamp.domain.api.usecase;

import com.pragma.bootcamp.domain.api.ITechnologyServicePort;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.ITechnologyPersistancePort;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

    private ITechnologyPersistancePort technologyPersistancePort;

    public TechnologyUseCase(ITechnologyPersistancePort technologyPersistancePort) {
        this.technologyPersistancePort = technologyPersistancePort;
    }

    @Override
    public void saveTechnology(Technology technology) {
        technologyPersistancePort.saveTechnology(technology);
    }

    @Override
    public Technology getTechnology(String name) {
        return technologyPersistancePort.getTechnology(name);
    }

    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendent) {
        return technologyPersistancePort.getAllTechnologies(page, size, isAscendent);
    }

    @Override
    public Technology updateTechnology(Technology technology) {
        return technologyPersistancePort.updateTechnology(technology);
    }

    @Override
    public void deleteTechnology(Long id) {
        technologyPersistancePort.deleteTechnology(id);
    }
}
