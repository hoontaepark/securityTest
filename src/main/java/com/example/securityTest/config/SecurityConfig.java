package com.example.securityTest.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ //Bcrypt 생성자 메소드
        return new BCryptPasswordEncoder(); //자동으로 Bcrypt 만들어줌.
    }

    @Bean //시큐리티 필터체인 빈 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http    //람다식으로 선언, 특정 경로에 요청을 진행함, 인가에 대한 작업
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/login").permitAll()//특정 경로에 요청을 진행함.
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("USER","ADMIN") //유저의 경로는 **와일드카드로 선언.
                        .anyRequest().authenticated() //이외 경로의 대한 처리 로그인한 사용자만 접근할수있도록 authenticated
                ); //특정권한에 대한 접근을 선언.

        http
                .formLogin((auth)->auth.loginPage("/login") //Login페이지 경로설정
                        .loginProcessingUrl("/loginProc") //로그인한 데이터를 특정한 경로로 보냄
                        .permitAll()  
                );

        http
                .csrf((auth)->auth.disable()); //csrf 잠깐 disable 시킴

        return http.build(); //리턴값으로 http리턴.


    }


}
