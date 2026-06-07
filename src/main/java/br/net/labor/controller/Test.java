package br.net.labor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ts")
public class Test {

    @GetMapping
    public String hello(){
        return "String gg";
    }

}
