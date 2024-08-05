package com.emergency_link.emergency_link.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping("/api/main")
    public ResponseEntity<Map<String,String>> checkLogin(){
        Map<String, String> responseBody = new HashMap<>();


        return ResponseEntity.ok().body(responseBody);
    }
}
