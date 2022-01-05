package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.model.Role;
import com.github.mraksveta.spittr.model.Spitter;
import com.github.mraksveta.spittr.repository.SpitterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SpitterUserDetailsServiceTest {
    @Mock
    private SpitterRepository spitterRepository;

    private SpitterUserDetailsService spitterUserDetailsService;

    @BeforeEach
    public void setUp() {
        spitterUserDetailsService = new SpitterUserDetailsService(spitterRepository);
    }

    @Test
    public void successLoadByUsername() {
        Spitter spitter = new Spitter(1L, "Ann", "Monster", "ann.monster",
                "ann.monster111", "ann.monster@freemail.org", false);
        spitter.setRoles(new ArrayList<>());
        spitter.addRole(new Role("SPITTER"));

        given(spitterRepository.findByUsername("ann.monster"))
                .willReturn(Optional.of(spitter));

        UserDetails userDetails = spitterUserDetailsService.loadUserByUsername("ann.monster");

        assertEquals(spitter.getUsername(), userDetails.getUsername());
        assertEquals(spitter.getPassword(), userDetails.getPassword());
    }

    @Test
    public void throwExceptionWhenNoUser() {
        given(spitterRepository.findByUsername("ann.monster"))
                .willReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> spitterUserDetailsService.loadUserByUsername("ann.monster"));
    }

    @Test
    public void throwExceptionWhenUserHasNoRoles() {
        Spitter spitter = new Spitter(1L, "Ann", "Monster", "ann.monster",
                "ann.monster111", "ann.monster@freemail.org", false);
        spitter.setRoles(new ArrayList<>());

        given(spitterRepository.findByUsername("ann.monster"))
                .willReturn(Optional.of(spitter));

        assertThrows(UsernameNotFoundException.class,
                () -> spitterUserDetailsService.loadUserByUsername("ann.monster"));
    }
}