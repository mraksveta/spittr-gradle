package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.exception.SpitterNotFoundException;
import com.github.mraksveta.spittr.model.Spitter;
import com.github.mraksveta.spittr.repository.SpitterRepository;
import com.github.mraksveta.spittr.service.SpitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpitterServiceImplTest {
    @Mock
    private SpitterRepository spitterRepository;

    private SpitterService spitterService;

    @BeforeEach
    public void setUp() {
        this.spitterService = new SpitterServiceImpl(spitterRepository, passwordEncoder);
    }

    @Test
    public void canRetrieveSpittrIfPresent() {
        Spitter expectedSpitter = new Spitter(1L, "Ann", "Monster",
                "ann.monster", "ann.monster111",
                "ann.monster@freemail.org", false);

        given(spitterRepository.findByUsername("ann.monster"))
                .willReturn(Optional.of(expectedSpitter));

        Spitter actualSpitter = spitterService.findByUsername("ann.monster");

        assertEquals(expectedSpitter, actualSpitter);
    }

    @Test
    public void throwExceptionWhenNoPresent() {
        given(spitterRepository.findByUsername("ann.monster"))
                .willReturn(Optional.empty());

        assertThrows(SpitterNotFoundException.class, () -> spitterService.findByUsername("ann.monster"));
    }

    @Test
    public void shouldSaveSuccess() {
        Spitter spitter = new Spitter(1L, "Ann", "Monster",
                "ann.monster", "ann.monster111",
                "ann.monster@freemail.org", false);

        spitterService.save(spitter);

        verify(spitterRepository).save(spitter);
    }
}