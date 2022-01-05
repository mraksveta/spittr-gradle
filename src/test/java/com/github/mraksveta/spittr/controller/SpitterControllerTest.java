package com.github.mraksveta.spittr.controller;

import com.github.mraksveta.spittr.exception.SpitterNotFoundException;
import com.github.mraksveta.spittr.handler.AppWideExceptionHandler;
import com.github.mraksveta.spittr.model.Spitter;
import com.github.mraksveta.spittr.service.SpitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class SpitterControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SpitterService spitterService;

    @InjectMocks
    private SpitterController spitterController;

    @BeforeEach
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = standaloneSetup(spitterController)
                .setControllerAdvice(new AppWideExceptionHandler())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("registerForm"))
                .andExpect(model().attributeExists("spitter"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        Spitter spitter = new Spitter(null, "Ann", "Morgan",
                "ann.morgan", "ann.morgan123", "ann.morgan@freemail.org", false);

        MockHttpServletRequestBuilder postRequestBuilder = post("/spitter/register")
                .param("firstName", "Ann")
                .param("lastName", "Morgan")
                .param("username", "ann.morgan")
                .param("password", "ann.morgan123")
                .param("email", "ann.morgan@freemail.org")
                .param("updateByEmail", "off");

        mockMvc.perform(postRequestBuilder)
                .andExpect(redirectedUrl("/spitter/ann.morgan"));

        verify(spitterService).save(spitter);
    }

    @Test
    public void shouldNotProcessRegistration() throws Exception {
        MockHttpServletRequestBuilder postRequestBuilder = post("/spitter/register")
                .param("firstName", "1")
                .param("lastName", "1")
                .param("username", "1")
                .param("password", "1")
                .param("email", "1")
                .param("updateByEmail", "off");

        mockMvc.perform(postRequestBuilder)
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldShowSpitterProfile() throws Exception {
        Spitter spitter = new Spitter(1L, "Ann", "Morgan", "ann.morgan",
                "ann.morgan123", "ann.morgan@freemail.org", false);

        given(spitterService.findByUsername("ann.morgan"))
                .willReturn(spitter);

        mockMvc.perform(get("/spitter/ann.morgan"))
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("spitter"))
                .andExpect(model().attribute("spitter", spitter))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldNotShowProfile() throws Exception {
        given(spitterService.findByUsername("unknown.user"))
                .willThrow(new SpitterNotFoundException());

        mockMvc.perform(get("/spitter/unknown.user"))
                .andExpect(view().name("error/not_found"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void shouldShowLoginPage() throws Exception {
        mockMvc.perform(get("/spitter/login"))
                .andExpect(view().name("login"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }
}