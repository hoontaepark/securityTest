package com.example.securityTest.dto;

import com.example.securityTest.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;


    public CustomUserDetails(UserEntity userEntity){

        this.userEntity = userEntity;

    }
    @Override //사용자의 특정한 권한을 리턴함. Role값을 반환.
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        }); //role 리턴


        return collection;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {

        return userEntity.getUsername();
    }

    @Override //계정이 만료되었는지
    public boolean isAccountNonExpired() {

        return true; //default 값은 false true 변경함
    }

    @Override //계정이 잠겨있는지
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override //지금 계정이 사용가능한지
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override //비활성화
    public boolean isEnabled() {

        return true;
    }
}
