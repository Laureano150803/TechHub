package com.pragma.bootcamp.domain.model;

import java.util.List;

public class Bootcamp {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Capability> capabilities;
    public Bootcamp(Long id, String name, String description, List<Capability> capabilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capabilities = capabilities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public List<Capability> getCapabilities() {
        return capabilities;
    }
}
