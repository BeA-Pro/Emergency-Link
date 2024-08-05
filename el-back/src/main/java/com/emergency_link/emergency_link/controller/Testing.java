package com.emergency_link.emergency_link.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testing {
    @GetMapping("/testing")
    public String testing(){
        return "Hello!!!";
    }
}
