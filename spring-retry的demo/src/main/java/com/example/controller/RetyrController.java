package com.example.controller;

import com.example.service.RetryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetyrController {

    @Autowired
    private RetryServiceImpl service;

    @GetMapping("/retry")
    public String retry(String name) {
        String ret;
//        ret = service.retry(name);
        ret = service.retry1(name).toString();
        return ret;
    }

}
