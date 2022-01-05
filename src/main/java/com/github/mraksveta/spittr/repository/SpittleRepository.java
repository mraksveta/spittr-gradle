package com.github.mraksveta.spittr.repository;

import com.github.mraksveta.spittr.model.Spittle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpittleRepository extends JpaRepository<Spittle, Long> {
}
