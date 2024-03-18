package com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter;


import com.pragma.bootcamp.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.bootcamp.domain.exception.TechnologyAlreadyExistException;
import com.pragma.bootcamp.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TechnologyAdapterTest {

    private ITechnologyRepository technologyRepository;
    private ITechnologyEntityMapper technologyEntityMapper;
    private TechnologyAdapter technologyAdapter;

    @BeforeEach
    public void setup() {
        technologyRepository = mock(ITechnologyRepository.class);
        technologyEntityMapper = mock(ITechnologyEntityMapper.class);
        technologyAdapter = new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @DisplayName("Given new Technology when SaveTechnology the Technology is saved")
    @Test
    public void saveTechnology() {

        AddTechnologyRequest technologyRequest = new AddTechnologyRequest("Java", "Lenguaje robusto");
        TechnologyEntity technologyEntity = new TechnologyEntity();
        technologyEntity.setId(1L);
        Technology technology = new Technology(1L, technologyRequest.getName(), technologyRequest.getDescription());


        when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);


        when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.empty());


        technologyAdapter.saveTechnology(technology);


        Mockito.verify(technologyRepository).save(technologyEntity);
    }

    @Test
    public void givenExistingTechnology_whenSaveTechnology_thenThrowTechnologyAlreadyExistException() {
        // Arrange
        AddTechnologyRequest technologyRequest = new AddTechnologyRequest("Java", "Lenguaje robusto");
        Technology technology = new Technology(1L, technologyRequest.getName(), technologyRequest.getDescription());

        // Stubbing behavior of technologyRepository
        when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.of(new TechnologyEntity()));

        // Act and assert
        assertThrows(TechnologyAlreadyExistException.class, () -> {
            technologyAdapter.saveTechnology(technology);
        });
    }
}
