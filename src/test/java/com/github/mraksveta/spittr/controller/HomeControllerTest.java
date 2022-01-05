package com.github.mraksveta.spittr.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {
    private HomeController homeController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        homeController = new HomeController();
        mockMvc = standaloneSetup(homeController).build();
    }

    @Test
    public void shouldShowHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
        mockMvc.perform(get("/homepage"))
                .andExpect(view().name("home"));
    }
}