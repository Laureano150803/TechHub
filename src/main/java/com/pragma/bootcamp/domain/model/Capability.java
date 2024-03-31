package com.pragma.bootcamp.domain.model;


import java.util.List;
import java.util.Objects;


public class Capability {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Technology> technologies;

    public Capability(Long id, String name, String description, List<Technology> technologies){
        this.id = id;
        this.name = name;
        this.description=description;
        this.technologies = technologies;
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

    public List<Technology> getTechnologies() {
        return technologies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Capability that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(technologies, that.technologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, technologies);
    }
}
