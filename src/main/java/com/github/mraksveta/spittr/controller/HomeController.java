package com.github.mraksveta.spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: mraksveta
 * Date: 02.11.21
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {
    @GetMapping
    public String home() {
        return "home/home";
    }
}
