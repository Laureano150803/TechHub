package com.pragma.bootcamp.adapters.driven.jpa.mysql.repository;

import com.pragma.bootcamp.adapters.driven.jpa.mysql.entity.CapabilityEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICapabilityRepository extends JpaRepository<CapabilityEntity, Long> {
  Optional<CapabilityEntity> findByName(String name);
  Page <CapabilityEntity> findAll(Pageable page);
}
