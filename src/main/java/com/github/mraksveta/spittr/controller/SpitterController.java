package com.github.mraksveta.spittr.controller;

import com.github.mraksveta.spittr.model.Spitter;
import com.github.mraksveta.spittr.service.SpitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mraksveta
 * Date: 03.11.21
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/spitters")
public class SpitterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpitterController.class);

    private final SpitterService spitterService;

    public SpitterController(SpitterService spitterService) {
        this.spitterService = spitterService;
    }


    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("spitters/registerForm", "spitter", new Spitter());
    }

    @PostMapping("/register")
    public String processRegistration(@Valid Spitter spitter, BindingResult errors,
                                      RedirectAttributes redirectAttributes, HttpServletRequest req) {
        if (errors.hasErrors()) {
            return "spitters/registerForm";
        }

        spitterService.save(spitter);

        redirectAttributes.addAttribute("username", spitter.getUsername());
        return "redirect:/spitter/{username}";
    }

    @GetMapping("/{username}")
    public String showSpitterProfile(@PathVariable String username, Model model) {
        Spitter spitter = spitterService.findByUsername(username);
        model.addAttribute("spitter", spitter);
        return "spitters/showProfile";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "spitters/loginPage";
    }

    @GetMapping("/logout")
    public String showLogoutPage() {
        return "spitters/logoutPage";
    }
}
