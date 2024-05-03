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

    @Bean//빈등록, 해시암호화임.
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ //Bcrypt 생성자 메소드
        return new BCryptPasswordEncoder(); //자동으로 Bcrypt 만들어줌.
    }

    @Bean //시큐리티 필터체인 빈 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http    //람다식으로 선언, 특정 경로에 요청을 진행함, 인가에 대한 작업
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/login","/LoginProc","/join","/joinProc").permitAll()//특정 경로에 요청을 진행함.
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

        http
                .sessionManagement((auth)->auth
                        .maximumSessions(1) //다중로그인 갯수
                        .maxSessionsPreventsLogin(true));//다중로그인 갯수 초과했을경우 true로 하면 새로운 로그인 제한,
                                                        // false 하면 세션 하나 삭제하고 로그인시킴
        http
                .sessionManagement((auth)->auth
                                .sessionFixation().changeSessionId()//세션고정보호
                        //세션 고정공격 보호를 위한 설정
                );
                //.none:로그인 시 세션 정보 변경안함 .newSession:로그인 시 세션 새로생성
                //.changeSessionId:로그인 시 동일한 세션에 대한 id변경


        return http.build(); //리턴값으로 http리턴.




    }


}
