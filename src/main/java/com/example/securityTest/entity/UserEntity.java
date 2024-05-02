package com.example.securityTest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true) //회원이름 중복안되게 설정.
    private String username; //username

    private String password; //password

    private String role; //권한

}
