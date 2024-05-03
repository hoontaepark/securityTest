package com.example.securityTest.service;


import com.example.securityTest.dto.CustomUserDetails;
import com.example.securityTest.entity.UserEntity;
import com.example.securityTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; //필드로 주입함, 이거는 특정 유저의 이름을 받아와야하기 떄문에 DB에 접근.

    @Override //username 검증
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username); //username인자를 넣어줌

        if(userData != null){
            return new CustomUserDetails(userData);
        }

        return null;
    }

}
