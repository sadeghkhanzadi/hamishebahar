package com.hamishebahar.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class MainController {
    @GetMapping("/error")
    public String loginPage() {
        return "error";
    }
}
