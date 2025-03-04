package com.example.firstproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcomeController {
    @GetMapping("/name")
    public String name(){
        return "My name is Faisal";
    }
    @GetMapping("/age")
    public String age(){
        return "My age is 25";
    }
    @GetMapping("/check/status")
    public String checkStatus(){
        return "My status is OK";
    }
    @GetMapping("/health")
    public String health(){
        return "Server health is OK";
    }
    @GetMapping("/names")
    public String[] names(){
        return new String[]{"Faisal","Ali","Abdullah","Salem"};
    }
}
