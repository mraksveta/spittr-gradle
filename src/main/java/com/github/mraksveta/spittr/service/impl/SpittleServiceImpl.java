package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.exception.*;
import com.github.mraksveta.spittr.model.*;
import com.github.mraksveta.spittr.repository.SpitterRepository;
import com.github.mraksveta.spittr.repository.SpittleRepository;
import com.github.mraksveta.spittr.service.FileManagementService;
import com.github.mraksveta.spittr.service.SpittleImageService;
import com.github.mraksveta.spittr.service.SpittleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SpittleServiceImpl implements SpittleService {
    private final SpittleRepository spittleRepository;
    private final SpitterRepository spitterRepository;
    private final FileManagementService fileManagementService;
    private final SpittleImageService spittleImageService;

    public SpittleServiceImpl(SpittleRepository spittleRepository, SpitterRepository spitterRepository, FileManagementService fileManagementService,
                              SpittleImageService spittleImageService) {
        this.spittleRepository = spittleRepository;
        this.spitterRepository = spitterRepository;
        this.fileManagementService = fileManagementService;
        this.spittleImageService = spittleImageService;
    }

    @Override
    @Transactional
    public List<Spittle> findAll(int page, int pageSize) {
        if (page < 1) {
            throw new InvalidPageException("Expected page greater then zero, but was " + page);
        }
        page = page - 1; // convert page to zero-based index
        Page<Spittle> spittlePage = spittleRepository.findAll(
                PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("timestamp"))));
        if (spittlePage.isEmpty()) {
            throw new SpittlesNotFoundException("not found spittles. SpittleServiceImpl.findAll(page=" + page
                    + ", pageSize=" + pageSize + ")");
        }
        return spittlePage.get().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Spittle find(long spittleId) {
        Optional<Spittle> spittle = spittleRepository.findById(spittleId);
        if (spittle.isEmpty()) {
            throw new SpittleNotFoundException("not found spittle. SpittleServiceImpl.find(spittleId="
                    + spittleId + ")");
        }
        return spittle.get();
    }

    @Override
    @Transactional
    public Spittle save(Spittle spittle) {
        // if spittle already exists and the same user requests update
        if (spittle.getId() != null && spittle.getSpitter().equals(getCurrentSpitter("SPITTER"))) {
            if (spittle.getImages() == null) spittle.setImages(new ArrayList<>());
            return spittleRepository.save(spittle);
        }
        // if spittle not exists then it is creating spittle
        spittle.setTimestamp(LocalDateTime.now());
        spittle.setSpitter(getCurrentSpitter("SPITTER"));
        return spittleRepository.save(spittle);
    }

    @Override
    @Transactional
    public Spittle save(Spittle spittle, MultipartFile... files) {
        Spittle savedSpittle = save(spittle);
        for (MultipartFile file : files) {
            String contentType = file.getContentType();
            String fileName = fileManagementService.uploadFile(file);
            SpittleImage image = new SpittleImage(fileName, new SpittleImageContentType(contentType));
            image.setSpittle(savedSpittle);
            spittleImageService.save(image);
        }
        return savedSpittle;
    }

    @Transactional
    private Spitter getCurrentSpitter(String ... roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) { // if user authenticated, not anonymous
            String username = authentication.getName(); // get username
            Optional<Spitter> spitter = spitterRepository.findByUsername(username); // find user by username
            if (spitter.isEmpty()) { // if user not found
                throw new SpitterNotFoundException("not found spitter for username '" + username + "' " +
                        "(username from authentication object)");
            }
            boolean hasRoles = spitter.get().getRoles()
                    .stream()
                    .map(Role::getRole)
                    .collect(Collectors.toList()).containsAll(List.of(roles)); // user has all specified roles?
            if (!hasRoles) {// if not, then permission denied
                throw new PermissionDeniedSpittrApplicationException("Spitter has not one of needed role "
                        + Arrays.toString(roles));
            }
            return spitter.get(); // if it has then return it
        } else {
            throw new PermissionDeniedSpittrApplicationException("User don't authenticate");
        }
    }
}
