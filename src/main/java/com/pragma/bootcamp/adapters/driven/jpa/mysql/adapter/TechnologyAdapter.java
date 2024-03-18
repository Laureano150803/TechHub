package com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.bootcamp.domain.exception.DescriptionExceedsMaxLengthException;
import com.pragma.bootcamp.domain.exception.ElementNotFoundException;
import com.pragma.bootcamp.domain.exception.NameExceedsMaxLengthException;
import com.pragma.bootcamp.domain.exception.TechnologyAlreadyExistException;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.ITechnologyPersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;



@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistancePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;


    @Override
    public void saveTechnology(Technology technology) {
        if (technologyRepository.findByName(technology.getName()).isPresent()){
            throw new TechnologyAlreadyExistException("This technology already exist");
        }
        if (technology.getName().length() > 50){
            throw new NameExceedsMaxLengthException("Technology name exceeds length 50");
        }
        if (technology.getDescription().length() > 90){
            throw new DescriptionExceedsMaxLengthException("Technology description exceeds length 90");
        }
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }

    @Override
    public Technology getTechnology(String name) {
        TechnologyEntity technology = technologyRepository.findByNameContaining(name)
                .orElseThrow(() -> new ElementNotFoundException("Element doesn't exist"));
        return technologyEntityMapper.toModel(technology);
    }

    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendent) {
        Sort.Direction direction = isAscendent ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<TechnologyEntity> technologyPage = technologyRepository.findAll(pageRequest);
        return technologyPage.getContent().stream()
                .map(technologyEntityMapper::toModel)
                .toList();
    }

    @Override
    public Technology updateTechnology(Technology technology) {
        return null;
    }

    @Override
    public void deleteTechnology(Long id) {
        if (technologyRepository.findById(id).isEmpty()){
            throw new ElementNotFoundException("");
        }
        technologyRepository.deleteById(id);
    }
}
