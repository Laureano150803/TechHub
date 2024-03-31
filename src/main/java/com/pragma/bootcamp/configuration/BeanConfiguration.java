package com.pragma.bootcamp.configuration;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter.CapabilityAdapter;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ICapabilityEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ICapabilityRepository;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import com.pragma.bootcamp.domain.api.ICapabilityServicePort;
import com.pragma.bootcamp.domain.api.ITechnologyServicePort;
import com.pragma.bootcamp.domain.api.usecase.BootcampUseCase;
import com.pragma.bootcamp.domain.api.usecase.CapabilityUseCase;
import com.pragma.bootcamp.domain.api.usecase.TechnologyUseCase;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
import com.pragma.bootcamp.domain.spi.ICapabilityPersistencePort;
import com.pragma.bootcamp.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    private final ICapabilityRepository capabilityRepository;
    private final ICapabilityEntityMapper capabilityEntityMapper;
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;


    @Bean
    public ITechnologyPersistencePort technologyPersistencePort(){
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }
    @Bean
    public ICapabilityPersistencePort capabilityPersistencePort(){
        return new CapabilityAdapter(capabilityRepository, capabilityEntityMapper, technologyRepository);
    }
    @Bean
    public IBootcampPersistencePort bootcampPersistencePort(){
        return new BootcampAdapter(bootcampRepository, bootcampEntityMapper, capabilityRepository);
    }
    @Bean
    public ITechnologyServicePort technologyServicePort(){
        return new TechnologyUseCase(technologyPersistencePort());
    }
    @Bean
    public ICapabilityServicePort capabilityServicePort(){
        return new CapabilityUseCase(capabilityPersistencePort());
    }
    @Bean
    public IBootcampServicePort bootcampServicePort(){
        return new BootcampUseCase(bootcampPersistencePort());
    }
}
