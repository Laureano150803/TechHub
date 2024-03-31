package com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ICapabilityEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ICapabilityRepository;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.bootcamp.configuration.Constants;
import com.pragma.bootcamp.domain.exception.ElementNotFound;
import com.pragma.bootcamp.domain.exception.ResourceAlreadyExist;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.ICapabilityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CapabilityAdapter implements ICapabilityPersistencePort {
    private final ICapabilityRepository capabilityRepository;
    private final ICapabilityEntityMapper capabilityEntityMapper;
    private final ITechnologyRepository technologyRepository;

    @Override
    public void saveCapability(Capability capability) {
        List<Technology> technologies = capability.getTechnologies();
        if (capabilityRepository.findByName(capability.getName()).isPresent()){
            throw new ResourceAlreadyExist(String.format(Constants.RESOURCE_ALREADY_EXIST, capability.getName()));
        }
        for (Technology technology : technologies) {
            technologyRepository.findById(technology.getId())
                    .orElseThrow(() -> new ElementNotFound(String.format(Constants.ELEMENT_NOT_FOUND, technology.getId())));
        }
        capabilityRepository.save(capabilityEntityMapper.toEntity(capability));
    }
    @Override
    public List<Capability> getAllCapabilities(Integer page, Integer size, boolean isAscendant, int byCant) {
        Sort.Order order = new Sort.Order(isAscendant ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        Page<CapabilityEntity> capabilityEntities;

        if (byCant > 0) {
            List<CapabilityEntity> filteredCapabilities = capabilityRepository.findAll().stream()
                    .filter(capabilityEntity -> capabilityEntity.getTechnologies().size() == byCant)
                    .collect(Collectors.toList());

            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), filteredCapabilities.size());
            capabilityEntities = new PageImpl<>(filteredCapabilities.subList(start, end), pageable, filteredCapabilities.size());
        } else {
            capabilityEntities = capabilityRepository.findAll(pageable);
        }

        return capabilityEntities.getContent().stream()
                .map(capabilityEntityMapper::toModel)
                .collect(Collectors.toList());
    }


}



