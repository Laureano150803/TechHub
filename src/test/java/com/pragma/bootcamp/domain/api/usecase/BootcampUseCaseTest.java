package com.pragma.bootcamp.domain.api.usecase;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
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
class BootcampUseCaseTest {

    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;
    @InjectMocks
    private BootcampUseCase bootcampUseCase;

    Technology technology = new Technology(1L, "newTechnology1", "important technology for automation");
    Technology technology2 = new Technology(2L, "newTechnology2", "important technology for automation");
    Technology technology3 = new Technology(3L, "newTechnology3", "important technology for automation");
    List<Technology> technologies = List.of(technology, technology2, technology3);

    Capability capability = new Capability(1L, "capability1", "goodCapability", technologies);
    Capability capability2 = new Capability(2L, "capability2", "goodCapability", technologies);
    Capability capability3 = new Capability(3L, "capability3", "goodCapability", technologies);
    List<Capability> capabilities = List.of(capability, capability2, capability3);

    @Test
    void saveBootcamp() {
        Bootcamp bootcamp = new Bootcamp(1L, "newBootcamp", "ExcelentBootcamp", capabilities);

        bootcampUseCase.saveBootcamp(bootcamp);

        verify(bootcampPersistencePort).saveBootcamp(bootcamp);
    }

    @Test
    void getAllBootcamp() {
        int page = 1;
        int size = 3;
        boolean isAscendant = true;
        int byCant = 3;

        Bootcamp bootcamp1 = new Bootcamp(1L, "newBootcamp1", "ExcelentBootcamp", capabilities);
        Bootcamp bootcamp2 = new Bootcamp(2L, "newBootcamp2", "ExcelentBootcamp", capabilities);
        Bootcamp bootcamp3 = new Bootcamp(3L, "newBootcamp3", "ExcelentBootcamp", capabilities);

        List<Bootcamp> expectedBootcampList = List.of(bootcamp1, bootcamp2, bootcamp3);

        given(bootcampPersistencePort.getAllBootcamp(page, size, isAscendant, byCant)).willReturn(expectedBootcampList);

        List<Bootcamp> result = bootcampUseCase.getAllBootcamp(page, size, isAscendant, byCant);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo(expectedBootcampList);
    }
}