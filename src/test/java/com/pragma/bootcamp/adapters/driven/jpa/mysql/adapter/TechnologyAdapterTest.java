package com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyAdapterTest {
    @Mock
    private  ITechnologyRepository technologyRepository;
    @Mock
    private  ITechnologyEntityMapper technologyEntityMapper;
    @InjectMocks
    private TechnologyAdapter technologyAdapter;
    @Test
    void saveTechnology() {
    }

    @Test
    void getAllTechnologies() {
    }
}