package com.pragma.bootcamp.adapters.driving.http.controller;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.adapter.CapabilityAdapter;
import com.pragma.bootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.bootcamp.adapters.driving.http.dto.request.AddCapabilityRequest;
import com.pragma.bootcamp.adapters.driving.http.dto.response.CapabilityResponse;
import com.pragma.bootcamp.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.bootcamp.adapters.driving.http.mapper.ICapabilityRequestMapper;
import com.pragma.bootcamp.adapters.driving.http.mapper.ICapabilityResponseMapper;
import com.pragma.bootcamp.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.pragma.bootcamp.domain.api.ICapabilityServicePort;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/capability")
@RequiredArgsConstructor
public class CapabilityRestControllerAdapter {
    private final ICapabilityServicePort capabilityServicePort;
    private final ICapabilityRequestMapper capabilityRequestMapper;
    private final ICapabilityResponseMapper capabilityResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addCapability(@Valid @RequestBody AddCapabilityRequest request){
        capabilityServicePort.saveCapability(capabilityRequestMapper.addRequestToCapability(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/")
    public ResponseEntity<List<CapabilityResponse>> getAllCapabilities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "true") boolean isAscendant,
            @RequestParam(defaultValue = "0") int byCant) {


        List<Capability> capabilities = capabilityServicePort.getAllCapabilities(page, size, isAscendant, byCant );
        List<CapabilityResponse> capabilityResponses = capabilities.stream()
                .map(capabilityResponseMapper::toCapabilityResponse)
                .toList();
        return ResponseEntity.ok(capabilityResponses);
    }
}
