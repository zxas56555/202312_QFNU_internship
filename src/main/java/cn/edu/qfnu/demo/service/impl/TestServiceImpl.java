package cn.edu.qfnu.demo.service.impl;

import cn.edu.qfnu.demo.service.TestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TestServiceImpl implements TestService {
    public String helloWithTime(){
        String str = "Hello world!";
        LocalDateTime now = LocalDateTime.now();

        String temp = str + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return temp;
    }
}
