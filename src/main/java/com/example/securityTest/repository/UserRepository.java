package com.example.securityTest.repository;

import com.example.securityTest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username); //existBy는 특정한 username이 where값으로 존재하는지 나타냄 존재하면 true
    UserEntity findByUsername(String username); //username으로 찾기
}
