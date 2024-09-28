package com.example;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Test

    public void test(){
        String password = "1";
        String salt = "1";
        String hashedPassword = new Md5Hash(password, salt).toHex();
        System.out.println(hashedPassword);
    }
}
