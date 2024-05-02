package com.example.securityTest.service;
import com.example.securityTest.dto.JoinDto;
import com.example.securityTest.entity.UserEntity;
import com.example.securityTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired //필드주입방식 나중에 생성자 방식으로 바꾸자
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto){

        //DB에 이미 동일한 password가 존재하는지 검증해야함. 리포지토리에 검증.

        UserEntity data = new UserEntity();
        data.setUsername(joinDto.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword())); //Bcrypt로 pw 암호화
        data.setRole("ROLE_USER"); //Role 강제주입.



        userRepository.save(data);
    }

}
