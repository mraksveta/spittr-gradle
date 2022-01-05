package com.github.mraksveta.spittr.service;

import com.github.mraksveta.spittr.model.SpittleImage;
import com.github.mraksveta.spittr.repository.SpittleImageRepository;

public interface SpittleImageService {
    SpittleImage save(SpittleImage spittleImage);

    SpittleImage findById(Long id);

    SpittleImage findByFileName(String fileName);
}
