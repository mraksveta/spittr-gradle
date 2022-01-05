package com.github.mraksveta.spittr.repository;

import com.github.mraksveta.spittr.model.SpittleImageContentType;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SpittleImageContentTypeRepository extends Repository<SpittleImageContentType, Integer> {
    Optional<SpittleImageContentType> findByContentType(String contentType);
}
