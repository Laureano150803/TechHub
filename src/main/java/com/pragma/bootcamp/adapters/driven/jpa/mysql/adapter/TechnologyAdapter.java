package com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.bootcamp.configuration.Constants;
import com.pragma.bootcamp.domain.exception.ResourceAlreadyExist;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;


@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;


    @Override
    public void saveTechnology(Technology technology) {
        if (technologyRepository.findByName(technology.getName()).isPresent()){
            throw new ResourceAlreadyExist(String.format(Constants.RESOURCE_ALREADY_EXIST, technology.getName()));
        }
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }
    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAscendant) {
        Sort.Direction direction = isAscendant ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<TechnologyEntity> technologyPage = technologyRepository.findAll(pageRequest);
        return technologyPage.getContent().stream()
                .map(technologyEntityMapper::toModel)
                .toList();
    }
}
