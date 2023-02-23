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
    public int testCache(@PathVariable int num){
        return testService.addAndReturn(num);
    }

    @GetMapping("/dto/{name}")
    public Object testCache(@PathVariable String name){
        return testService.returnDto(name);
    }
}
