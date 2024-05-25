package com.hamishebahar.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/error")
    public String loginPage() {
        return "error";
    }
}
