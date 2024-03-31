package com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ICapabilityRepository;
import com.pragma.bootcamp.configuration.Constants;
import com.pragma.bootcamp.domain.exception.ElementNotFound;
import com.pragma.bootcamp.domain.exception.ResourceAlreadyExist;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    private final ICapabilityRepository capabilityRepository;
    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        List<Capability> capabilities = bootcamp.getCapabilities();
        if (bootcampRepository.findByName(bootcamp.getName()).isPresent()){
            throw new ResourceAlreadyExist(String.format(Constants.RESOURCE_ALREADY_EXIST, bootcamp.getName()));
        }
        for(Capability capability : capabilities){
            capabilityRepository.findById(capability.getId())
                    .orElseThrow(() -> new ElementNotFound(String.format(Constants.ELEMENT_NOT_FOUND, capability.getId())));
        }
        bootcampRepository.save(bootcampEntityMapper.toEntity(bootcamp));
    }

    @Override
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, boolean isAscendant, int byCant) {
        Sort.Order order = new Sort.Order(isAscendant ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        Page<BootcampEntity> bootcampEntities;

        if(byCant > 0){
            List<BootcampEntity> filteredBootcamp = bootcampRepository.findAll().stream()
                    .filter(bootcampEntity -> bootcampEntity.getCapabilities().size() == byCant)
                    .collect(Collectors.toList());
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), filteredBootcamp.size());
            bootcampEntities = new PageImpl<>(filteredBootcamp.subList(start, end), pageable, filteredBootcamp.size());
        }else{
            bootcampEntities = bootcampRepository.findAll(pageable);
        }
        return bootcampEntities.getContent().stream()
                .map(bootcampEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}
