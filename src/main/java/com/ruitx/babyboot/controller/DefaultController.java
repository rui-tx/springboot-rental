package com.ruitx.babyboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping(path = "/")
    public String webRoot() {
        return "This is the root of the application";
    }

}
