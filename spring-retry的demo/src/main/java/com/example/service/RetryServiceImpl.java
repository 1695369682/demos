package com.example.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryServiceImpl {
    int count = 0;
    int count1 = 0;

    @Retryable(value = {RuntimeException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public String retry(String name) {
        if ("admin".equals(name)) {
            System.out.println("瞅你咋地" + (++count));
            if (count == 6)
                return "111";
            System.out.println(1 / 0);

//            throw new RuntimeException("瞅你咋地"+(count));
        }
        return "retry success";
    }
    @Retryable(value = {RuntimeException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public Integer retry1(String name) {
        if ("admin".equals(name)) {
            System.out.println("瞅你咋地" + (++count1));
            if (count1 == 6)
                return 1;
            System.out.println(1 / 0);

//            throw new RuntimeException("瞅你咋地"+(count));
        }
        return 0;
    }
    @Recover
    public String defaultmethod(String msg) {
        System.err.println("回复方法执行");
        return "default?:" + msg;
    }
    @Recover
    public Integer defaultmethod1(String msg) {
        System.err.println("回复方法1执行");
        return 11;
    }
}
