package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.exception.SpittleNotFoundException;
import com.github.mraksveta.spittr.exception.SpittlesNotFoundException;
import com.github.mraksveta.spittr.model.Spittle;
import com.github.mraksveta.spittr.repository.SpittleRepository;
import com.github.mraksveta.spittr.service.SpittleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpittleServiceImplTest {
        @Mock
        private SpittleRepository spittleRepository;
        private SpittleService spittleService;

        @BeforeEach
        public void setUp() throws IOException {
            this.spittleService = new SpittleServiceImpl(spittleRepository, fileManagementService, spittleImageService);
        }

        @Test
        public void successfulFindAll() {
            List<Spittle> expectedSpittles = spittleRange(1, 21);
            given(spittleRepository.findAll(any(Pageable.class)))
                    .willReturn(new PageImpl<>(expectedSpittles));

            List<Spittle> actualSpittles = spittleService.findAll(1, 20);

            assertEquals(expectedSpittles, actualSpittles);
            verify(spittleRepository).findAll(PageRequest.of(1, 20, Sort.by(Sort.Order.desc("timestamp"))));
        }

        @Test
        public void throwExceptionWhenPageIsEmpty() {
            given(spittleRepository.findAll(any(Pageable.class)))
                    .willReturn(Page.empty());

            assertThrows(SpittlesNotFoundException.class, () -> spittleService.findAll(1, 20));
            verify(spittleRepository).findAll(PageRequest.of(1, 20, Sort.by(Sort.Order.desc("timestamp"))));
        }

        @Test
        public void successfulFindSpittle() {
            Spittle expectedSpittle = new Spittle(10L, "Message of spittle with ID 10",
                    LocalDateTime.now());

            given(spittleRepository.findById(10L))
                    .willReturn(Optional.of(expectedSpittle));

            Spittle actualSpittle = spittleService.find(10L);

            assertEquals(expectedSpittle, actualSpittle);
        }

        @Test
        public void throwExceptionWhenNoSpittle() {
            given(spittleRepository.findById(10L))
                    .willReturn(Optional.empty());
            assertThrows(SpittleNotFoundException.class, () -> spittleService.find(10L));
        }

        private List<Spittle> spittleRange(int fromIncludes, int toExcludes) {
            List<Spittle> spittles = new ArrayList<>();
            for (long id = fromIncludes; id < toExcludes; id++) {
                spittles.add(new Spittle(id, "message" + id, LocalDateTime.now()));
            }
            return spittles;
        }
}