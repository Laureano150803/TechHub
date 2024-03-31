package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {
    void saveBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamp(Integer page, Integer size, boolean isAscendant, int byCant);
}
