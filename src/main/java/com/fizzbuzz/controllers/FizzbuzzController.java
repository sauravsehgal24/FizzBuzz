package com.fizzbuzz.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class FizzbuzzController {
    
    @GetMapping("/")    
    public String getTest(){
        return "hi";
    }
}
