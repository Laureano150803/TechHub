package com.pragma.bootcamp.configuration;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.bootcamp.domain.api.ITechnologyServicePort;
import com.pragma.bootcamp.domain.api.usecase.TechnologyUseCase;
import com.pragma.bootcamp.domain.spi.ITechnologyPersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyPersistancePort technologyPersistancePort(){
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }
    @Bean
    public ITechnologyServicePort technologyServicePort(){
        return new TechnologyUseCase(technologyPersistancePort());
    }
}
