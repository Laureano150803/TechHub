package com.pragma.bootcamp.adapters.driving.http.controller;

import com.pragma.bootcamp.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.bootcamp.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.bootcamp.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.bootcamp.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import com.pragma.bootcamp.domain.model.Bootcamp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addBootcamp(@Valid @RequestBody AddBootcampRequest request){
        bootcampServicePort.saveBootcamp(bootcampRequestMapper.addRequestToBootcamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/")
    public ResponseEntity<List<BootcampResponse>> getAllBootcamp(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "true") boolean isAscendant,
            @RequestParam(defaultValue = "0") int byCant){
        List<Bootcamp> bootcamps = bootcampServicePort.getAllBootcamp(page, size, isAscendant, byCant);
        List<BootcampResponse> bootcampResponses = bootcamps.stream()
                .map(bootcampResponseMapper::toBootcampResponse)
                .toList();
        return ResponseEntity.ok(bootcampResponses);
    }
}
