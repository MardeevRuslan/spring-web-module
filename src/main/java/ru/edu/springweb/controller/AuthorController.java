package ru.edu.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    @GetMapping("/")
    public String authorPage() {
        return "author";
    }

    @GetMapping("/hobby")
    public String hobbyPage() {
        return "hobby";
    }
}
