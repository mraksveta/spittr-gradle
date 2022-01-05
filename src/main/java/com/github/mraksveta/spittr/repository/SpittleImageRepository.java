package com.github.mraksveta.spittr.repository;

import com.github.mraksveta.spittr.model.SpittleImage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpittleImageRepository extends CrudRepository<SpittleImage,Long> {
    Optional<SpittleImage> findByFileName(String fileName);
}
