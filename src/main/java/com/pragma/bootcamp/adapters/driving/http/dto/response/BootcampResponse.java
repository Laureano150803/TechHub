package com.pragma.bootcamp.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BootcampResponse {
    private Long id;
    private String name;
    private String description;
    private List<CapabilitiesInBootcamp> capabilities;
}
