package com.example.web.exception;

/**
 * 自定义异常类
 */
public class BusinessExcetion extends RuntimeException {

    public BusinessExcetion(String msg){
        super(msg);
    }
}
