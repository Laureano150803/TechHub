package com.pragma.bootcamp.adapters.driving.http.controller;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.pragma.bootcamp.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.bootcamp.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.bootcamp.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.bootcamp.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.pragma.bootcamp.domain.api.ITechnologyServicePort;
import com.pragma.bootcamp.domain.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/technology")
@RequiredArgsConstructor
public class TechnologyRestControllerAdapter {
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;
    private final TechnologyAdapter technologyAdapter;

    @PostMapping("/")
    public ResponseEntity<Void> addTechnology(@RequestBody AddTechnologyRequest request){
        technologyServicePort.saveTechnology(technologyRequestMapper.addRequestToTechnology(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<TechnologyResponse>> getAllTechnologies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "true") boolean isAscendent) {

        List<Technology> technologies = technologyAdapter.getAllTechnologies(page, size, isAscendent);
        List<TechnologyResponse> technologyResponses = technologies.stream()
                .map(technologyResponseMapper::toTechnologyResponse)
                .toList();

        return ResponseEntity.ok(technologyResponses);
    }
}
