package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.JoinData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class JoinController {

    @PostMapping("/join")
    @ResponseBody
    public String login(@RequestBody JoinData joinData) {
        System.out.println(joinData);
        return "Login successful";
    }
}