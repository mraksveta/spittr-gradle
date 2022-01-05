package com.github.mraksveta.spittr.controller;

import com.github.mraksveta.spittr.model.Spittle;
import com.github.mraksveta.spittr.service.SpittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created with IntelliJ IDEA.
 * User: mraksveta
 * Date: 03.11.21
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private final SpittleService spittleService;

    @Autowired
    public SpittleController(SpittleService spittleService) {
        this.spittleService = spittleService;
    }

    @GetMapping
    public ModelAndView spittles(@RequestParam(value="page", defaultValue = "1") int page,
                                 @RequestParam(value="pageSize", defaultValue = "20") int pageSize) {
        return new ModelAndView("spittles/showSpittles", "spittles",
                spittleService.findAll(page, pageSize));
    }

    @GetMapping("/{spittleId}")
    public String showSpittle(@PathVariable long spittleId, Model model) {
        Spittle spittle = spittleService.find(spittleId);
        model.addAttribute("spittle", spittle);
        return "spittles/showSpittle";
    }

    @GetMapping("/create")
    public String createSpittleForm(Model model) {
        model.addAttribute("spittle", new Spittle());
        return "spittles/createSpittleForm";
    }

    @PostMapping
    public String createSpittle(@RequestParam(value = "files") MultipartFile[] files, Spittle requestSpittle, RedirectAttributes redirectAttributes) {
        Spittle spittle = spittleService.save(requestSpittle, files);
        redirectAttributes.addAttribute("spittleId", spittle.getId());
        return "redirect:/spittles/{spittleId}";
    }
}
