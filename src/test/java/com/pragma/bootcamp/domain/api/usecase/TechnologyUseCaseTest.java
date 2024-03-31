package com.pragma.bootcamp.domain.api.usecase;

import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {
    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;
    @InjectMocks
    private TechnologyUseCase technologyUseCase;

    @Test
    @DisplayName("Given new Technology when SaveTechnology the Technology is saved")
    void saveTechnology() {

        Technology technology = new Technology(1L, "newTechnology", "important technology for automation");

        technologyUseCase.saveTechnology(technology);

        verify(technologyPersistencePort).saveTechnology(technology);
    }

    @Test
    @DisplayName("Given new Technology, when retrieving all Technologies, then the Technology is saved and returned successfully")
    void getAllTechnologies() {

        int page = 1;
        int size = 3;
        boolean isAscendant = true;

        Technology technology = new Technology(1L, "newTechnology1", "important technology for automation");
        Technology technology2 = new Technology(2L, "newTechnology2", "important technology for automation");
        Technology technology3 = new Technology(3L, "newTechnology3", "important technology for automation");

        List<Technology> expectedTechnologies = List.of(technology, technology2, technology3);

        given(technologyPersistencePort.getAllTechnologies(page, size, isAscendant)).willReturn(expectedTechnologies);

        List<Technology> result = technologyUseCase.getAllTechnologies(page, size, isAscendant);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo(expectedTechnologies);
    }
}