package com.example.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by shichp on 2016/12/23.
 */
@Data
@Entity
//public class Reader extends User {
public class Reader implements UserDetails {
    @Id
    private String username;
    private String fullname;
    private String password;

    /**
     * 授予READER
     * 权限
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("READER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    //不过期
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    //不会被锁定
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    //不会被撤销
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
