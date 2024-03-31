package com.pragma.bootcamp.domain.spi;

import com.pragma.bootcamp.domain.model.Capability;


import java.util.List;

public interface ICapabilityPersistencePort {
    void saveCapability(Capability capability);
    List<Capability> getAllCapabilities(Integer page, Integer size, boolean isAscendant, int byCant);
}
