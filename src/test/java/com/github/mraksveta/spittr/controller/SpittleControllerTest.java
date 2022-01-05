package com.github.mraksveta.spittr.controller;

import com.github.mraksveta.spittr.exception.SpittleNotFoundException;
import com.github.mraksveta.spittr.handler.AppWideExceptionHandler;
import com.github.mraksveta.spittr.model.Spittle;
import com.github.mraksveta.spittr.service.SpittleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class SpittleControllerTest {
    @Mock
    private SpittleService spittleService;

    @InjectMocks
    private SpittleController spittleController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(spittleController)
                .setControllerAdvice(new AppWideExceptionHandler())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        Spittle spittle = new Spittle(100L, "Test", LocalDateTime.now());
        given(spittleService.find(100))
                .willReturn(spittle);

        mockMvc.perform(get("/spittles/100"))
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", spittle));
    }

    @Test
    public void canRetrieveByIdWhenNotExists() throws Exception {
        given(spittleService.find(100L))
                .willThrow(new SpittleNotFoundException());

        mockMvc.perform(get("/spittles/100"))
                .andExpect(view().name("error/not_found"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}