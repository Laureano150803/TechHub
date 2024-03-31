package com.pragma.bootcamp.domain.api.usecase;

import com.pragma.bootcamp.domain.api.ICapabilityServicePort;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.spi.ICapabilityPersistencePort;


import java.util.List;

public class CapabilityUseCase implements ICapabilityServicePort {
    private final ICapabilityPersistencePort capabilityPersistencePort;

    public CapabilityUseCase(ICapabilityPersistencePort capabilityPersistencePort) {
        this.capabilityPersistencePort = capabilityPersistencePort;
    }

    @Override
    public void saveCapability(Capability capability) {
        capabilityPersistencePort.saveCapability(capability);
    }
    @Override
    public List<Capability> getAllCapabilities(Integer page, Integer size, boolean isAscendant, int byCant) {
        return capabilityPersistencePort.getAllCapabilities(page, size, isAscendant, byCant);
    }
}
