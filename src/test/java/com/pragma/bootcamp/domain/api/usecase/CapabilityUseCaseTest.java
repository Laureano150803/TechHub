package com.pragma.bootcamp.domain.api.usecase;

import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.ICapabilityPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CapabilityUseCaseTest {
    @Mock
    private ICapabilityPersistencePort capabilityPersistencePort;
    @InjectMocks
    private CapabilityUseCase capabilityUseCase;



    Technology technology = new Technology(1L, "newTechnology1", "important technology for automation");
    Technology technology2 = new Technology(2L, "newTechnology2", "important technology for automation");
    Technology technology3 = new Technology(3L, "newTechnology3", "important technology for automation");
    List<Technology> technologies = List.of(technology, technology2, technology3);


    @Test
    @DisplayName("Given new Capability when SaveCapability the Capability is saved")
    void saveCapability() {


        Capability capability = new Capability(1L, "newCapability", "Important capability", technologies);

        capabilityUseCase.saveCapability(capability);

        verify(capabilityPersistencePort).saveCapability(capability);
    }

    @Test
    @DisplayName("Given new Capability, when retrieving all Capability, then the Capability is saved and returned successfully")
    void getAllCapabilities() {
        int page = 1;
        int size = 3;
        boolean isAscendant = true;
        int byCant = 3;

        Capability capability = new Capability(1L, "capability1", "goodCapability", technologies);
        Capability capability2 = new Capability(2L, "capability2", "goodCapability", technologies);
        Capability capability3 = new Capability(3L, "capability3", "goodCapability", technologies);

        List<Capability> expectedCapabilities = List.of(capability, capability2, capability3);

        given(capabilityPersistencePort.getAllCapabilities(page, size, isAscendant, byCant)).willReturn(expectedCapabilities);

        List<Capability> result = capabilityUseCase.getAllCapabilities(page, size, isAscendant, byCant);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo(expectedCapabilities);
    }
}