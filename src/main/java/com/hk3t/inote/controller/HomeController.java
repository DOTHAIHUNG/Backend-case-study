package com.hk3t.inote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("homepage");
        return modelAndView;
    }
}
