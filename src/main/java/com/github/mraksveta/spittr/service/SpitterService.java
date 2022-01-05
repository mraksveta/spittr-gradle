package com.github.mraksveta.spittr.service;

import com.github.mraksveta.spittr.model.Spitter;

public interface SpitterService {
    Spitter findByUsername(String username);

    Spitter save(Spitter spitter);
}
