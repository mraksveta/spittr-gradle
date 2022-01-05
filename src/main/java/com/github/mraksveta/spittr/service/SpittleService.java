package com.github.mraksveta.spittr.service;

import com.github.mraksveta.spittr.model.Spittle;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpittleService {
    List<Spittle> findAll(int page, int pageSize);

    Spittle find(long spittleId);

    Spittle save(Spittle spittle);

    Spittle save(Spittle spittle, MultipartFile ... files);
}
