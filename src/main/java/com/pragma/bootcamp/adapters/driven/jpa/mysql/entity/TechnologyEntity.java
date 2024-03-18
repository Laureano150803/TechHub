package com.pragma.bootcamp.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "technology")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TechnologyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 90)
    private String description;
}
