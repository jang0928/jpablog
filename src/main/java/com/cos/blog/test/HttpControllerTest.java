package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController //특정 어노테이션 붙어있는 클래스 파일을 new 해서 스프링에서 관리
public class HttpControllerTest {
    private  static  final String TAG= "HtttpControllerTest";

    @GetMapping("/http/get")
    public String getTest(@RequestParam Member m) {
        return "get !!!";
    }
    @PostMapping("/http/post") //message-convert
    public String postTest(@RequestBody Member m ) {
        return  "post !!!"+ m.getId() +m.getEmail()+m.getPassword();
    }
    @PutMapping("/http/put")
    public String putTest() {
        return  "putTest !!!";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return  "deleteTest !!!";
    }

}
