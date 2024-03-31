package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Capability;


import java.util.List;

public interface ICapabilityServicePort {
    void saveCapability(Capability capability);
    List<Capability> getAllCapabilities(Integer page, Integer size, boolean isAscendant, int byCant);
}
