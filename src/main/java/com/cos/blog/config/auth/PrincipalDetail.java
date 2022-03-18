package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//
@Data
public class PrincipalDetail implements UserDetails {
   //콤포지션
    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    //계정이 가지고있는 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(()->{return "ROLE_"+user.getRole();
        });
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는지 ? true : 만료안됨
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정이 잠겨있는지 ?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정 비번이 만료되었나 ?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 인지 ?
    @Override
    public boolean isEnabled() {
        return true;
    }
}
