package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.exception.SpitterNotFoundException;
import com.github.mraksveta.spittr.model.Role;
import com.github.mraksveta.spittr.model.Spitter;
import com.github.mraksveta.spittr.repository.RoleRepository;
import com.github.mraksveta.spittr.repository.SpitterRepository;
import com.github.mraksveta.spittr.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class SpitterServiceImpl implements SpitterService {
    private final SpitterRepository spitterRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SpitterServiceImpl(SpitterRepository spitterRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.spitterRepository = spitterRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Spitter findByUsername(String username) {
        Optional<Spitter> spitter = spitterRepository.findByUsername(username);
        if (spitter.isEmpty()) {
            throw new SpitterNotFoundException("spitter not found. SpitterServiceImpl.findByUsername(username=" +
                    username + ")");
        }
        return spitter.get();
    }

    @Override
    @Transactional
    public Spitter save(Spitter spitter) {
        Objects.requireNonNull(spitter, "to SpitterService.save() was passed null");
        encodeSpitterPassword(spitter);
        addDefaultSpitterRole(spitter);
        return spitterRepository.save(spitter);
    }

    private void addDefaultSpitterRole(Spitter spitter) {
        if (spitter.getRoles() == null) {
            spitter.setRoles(new ArrayList<>());
        }
        if (spitter.getRoles().isEmpty()) {
            spitter.addRole(createRoleIfNotExists("SPITTER"));
        }
    }

    @Transactional
    private Role createRoleIfNotExists(String roleName) {
        Optional<Role> role = roleRepository.findByRole(roleName);
        if (role.isEmpty()) {
            Role createRole = new Role(roleName);
            roleRepository.save(createRole);
            return createRole;
        }
        return role.get();
    }

    private void encodeSpitterPassword(Spitter spitter) {
        spitter.setPassword(passwordEncoder.encode(spitter.getPassword()));
    }
}
