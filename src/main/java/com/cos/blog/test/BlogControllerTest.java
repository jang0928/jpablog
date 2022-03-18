package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //특정 어노테이션 붙어있는 클래스 파일을 new 해서 스프링에서 관리
public class BlogControllerTest {

    @GetMapping("test/hello")
    public String hello() {
        return "test2.html";

    }
}
