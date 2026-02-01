package com.example.poetrybackend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.poetrybackend.PoetryBackend;
import com.example.poetrybackend.Service.PoemService;

@RestController
@RequestMapping("/api")
public class MainController {
    PoemService poemService;

    public MainController(PoemService poemService){
        this.poemService = poemService;
    }

    @GetMapping("/poem")
    public String hello(@RequestParam(value = "id", defaultValue = "0") String id) {
        PoetryBackend.logger.info(id);
        return id.toString();
    }

    @GetMapping("/poem/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}