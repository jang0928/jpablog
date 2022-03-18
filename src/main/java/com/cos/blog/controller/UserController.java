package com.cos.blog.controller;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


//인증이 안된 사용자들이 출입할 경로 = /auth/** 허용
//그냥 주소가  / 이면 index.jsp
@Controller
public class UserController {
    @Value("${cos.key}")
    private String cosKey;

    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody
    String kakaoCallback(String code) {

        //POST 방식으로 key = value 데이터를 요청 (카카오로)
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "5a637510f0c02d730241a661e1af20ba");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);
        //httpHedader ,Body를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);
        //Http - Post 방식으로 요청 , response 변수의 응답
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        ObjectMapper obMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("카카오 엑세스 토큰 " + oAuthToken.getAccess_token());


        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-Type", "application/x-www-form-urlencoded");
        //HttpBody 오브젝트 생성

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(params, headers2);
        //Http - Post 방식으로 요청 , response 변수의 응답
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper obMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = obMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(kakaoProfile.getId() + " =====" + kakaoProfile.getKakao_account().getEmail());
        System.out.println("KAKAO USERNAME : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        UUID garbagePassword = UUID.randomUUID();
        User KakaoUser = User.builder().username(
                kakaoProfile.getKakao_account().getEmail() + "-" + kakaoProfile.getId()
        ).password(cosKey).email(kakaoProfile.getKakao_account().getEmail()).oauth("kakao").build();
        User originUser = userService.find(KakaoUser.getUsername());
        //회원 비회원 체크
        if (originUser.getUsername() == null) {

            System.out.println("자동 회원가입으로 진행합니다 ~~~");
            userService.save(KakaoUser);
        }
        //로그인 처리
        System.out.println("자동 로그인 !!");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(KakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";

    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}
