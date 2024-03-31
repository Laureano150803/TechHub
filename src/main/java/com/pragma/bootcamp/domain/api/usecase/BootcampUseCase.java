package com.pragma.bootcamp.domain.api.usecase;

import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;

import java.util.List;

public class BootcampUseCase implements IBootcampServicePort{
    private final IBootcampPersistencePort bootcampPersistencePort;

    public BootcampUseCase(IBootcampPersistencePort iBootcampPersistencePort) {
        this.bootcampPersistencePort = iBootcampPersistencePort;
    }


    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        bootcampPersistencePort.saveBootcamp(bootcamp);
    }

    @Override
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, boolean isAscendant, int byCant) {
        return bootcampPersistencePort.getAllBootcamp(page, size, isAscendant, byCant);
    }
}
