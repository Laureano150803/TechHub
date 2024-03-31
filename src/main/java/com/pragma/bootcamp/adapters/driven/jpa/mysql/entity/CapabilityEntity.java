package com.pragma.bootcamp.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "capability")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapabilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 90)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "capability_technology",
            joinColumns = @JoinColumn(name = "capability_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<TechnologyEntity> technologies = new ArrayList<>();
}
