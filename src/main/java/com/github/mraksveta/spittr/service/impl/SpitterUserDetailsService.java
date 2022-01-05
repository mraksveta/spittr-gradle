package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.model.Role;
import com.github.mraksveta.spittr.model.Spitter;
import com.github.mraksveta.spittr.repository.SpitterRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpitterUserDetailsService implements UserDetailsService {
    private final SpitterRepository spitterRepository;

    public SpitterUserDetailsService(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Spitter> spitter = spitterRepository.findByUsername(username);
        if (spitter.isEmpty()) {
            throw new UsernameNotFoundException("Can't find spitter with username=" + username);
        }
        if (spitter.get().getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User hasn't GrantedAuthority");
        }
        Spitter unpackedSpitter = spitter.get();
        return new User(unpackedSpitter.getUsername(), unpackedSpitter.getPassword(),
                convertRolesToAuthorities(unpackedSpitter.getRoles()));
    }


    private List<GrantedAuthority> convertRolesToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Role::getRole)
                .map("ROLE_"::concat)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
