package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    @GetMapping("/test/jsp")
    public String jspTest() {
        return "/test";
    }

    @GetMapping("/test/html")
    public String htmlTest() {
        return "/blog/js/user.test2.html";
    }
}

