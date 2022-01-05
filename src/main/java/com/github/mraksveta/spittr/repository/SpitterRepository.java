package com.github.mraksveta.spittr.repository;

import com.github.mraksveta.spittr.model.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpitterRepository extends JpaRepository<Spitter, Long> {
    Optional<Spitter> findByUsername(String username);
}
