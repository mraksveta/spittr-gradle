package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.exception.SpittleImageNotFoundException;
import com.github.mraksveta.spittr.exception.SpittrApplicationNotFoundException;
import com.github.mraksveta.spittr.model.SpittleImage;
import com.github.mraksveta.spittr.model.SpittleImageContentType;
import com.github.mraksveta.spittr.repository.SpittleImageContentTypeRepository;
import com.github.mraksveta.spittr.repository.SpittleImageRepository;
import com.github.mraksveta.spittr.service.SpittleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SpittleImageServiceImpl implements SpittleImageService {
    private final SpittleImageRepository spittleImageRepository;
    private final SpittleImageContentTypeRepository spittleImageContentTypeRepository;

    @Autowired
    public SpittleImageServiceImpl(SpittleImageRepository spittleImageRepository,
                                   SpittleImageContentTypeRepository spittleImageContentTypeRepository) {
        this.spittleImageRepository = spittleImageRepository;
        this.spittleImageContentTypeRepository = spittleImageContentTypeRepository;
    }

    @Override
    @Transactional
    public SpittleImage save(SpittleImage spittleImage) {
        String contentType = spittleImage.getContentType().getContentType();
        spittleImage.setContentType(getSpittleImageContentTypeForName(contentType));
        return spittleImageRepository.save(spittleImage);
    }

    @Override
    @Transactional
    public SpittleImage findById(Long id) {
        Optional<SpittleImage> spittleImage = spittleImageRepository.findById(id);

        if (spittleImage.isEmpty()) {
            throw new SpittleImageNotFoundException("not found spittle image for id " + id);
        }

        return spittleImage.get();
    }

    @Override
    @Transactional
    public SpittleImage findByFileName(String fileName) {
        Optional<SpittleImage> spittleImage = spittleImageRepository.findByFileName(fileName);

        if (spittleImage.isEmpty()) {
            throw new SpittleImageNotFoundException("not found spittle image for fileName=" + fileName);
        }

        return spittleImage.get();
    }

    @Transactional
    private SpittleImageContentType getSpittleImageContentTypeForName(String spittleImageContentTypeName) {
        Optional<SpittleImageContentType> spittleImageContentType = spittleImageContentTypeRepository
                .findByContentType(spittleImageContentTypeName);

        if (spittleImageContentType.isEmpty()) {
            throw new SpittrApplicationNotFoundException("SpittleImageContentType not found " +
                    "for spittleImageContentTypeName="+spittleImageContentTypeName);
        }

        return spittleImageContentType.get();
    }
}
