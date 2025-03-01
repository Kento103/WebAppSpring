package com.kenspringapp.springapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class PageController {
    @RequestMapping("/")
    public String topPage() {
        return "こんにちは！HelloWorld!!";
    }
    
}
