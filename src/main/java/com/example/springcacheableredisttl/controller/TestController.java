package com.example.springcacheableredisttl.controller;

import com.example.springcacheableredisttl.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test/{num}")
    public Object testCache(@PathVariable int num){
        return testService.addAndReturn(num);
    }
}
