package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //Bean 에 등록해줌
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encode;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public int save(User user) {
        try {
            String rawPassword = user.getPassword();
            String encPassword = encode.encode(rawPassword);
            user.setRole(RoleType.USER);
            user.setPassword(encPassword);
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Transactional
    public void update(User user) {
        User persist = userRepository.findById(user.getId()).orElseThrow(
                () -> {
                    return new IllegalArgumentException("회원찾기 실패");
                }
        );
        //카카오 로그인은 수정 못하게 분기처리
        if (persist.getOauth() == null || persist.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encode.encode(rawPassword);
            persist.setPassword(encPassword);
            persist.setEmail(user.getEmail());
        }
        //Session

        //영속화된 pserist 객체의 변화가 감지되면 더티체킹이되어 update 문을 날려준다 .
    }

    @Transactional(readOnly = true)
    public User find(String username) {
        User user = userRepository.findByUsername(username).orElseGet(
                () -> {
                    return new User();
                });
        return user;
    }
}
