package com.pragma.bootcamp.domain.spi;

import com.pragma.bootcamp.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamp(Integer page, Integer size, boolean isAscendant, int byCant);
}
