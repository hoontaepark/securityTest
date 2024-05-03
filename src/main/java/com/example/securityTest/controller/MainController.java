package com.example.securityTest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainP(Model model){ //세션을 넘겨주기 위해 Model 인터페이스 인자로 넘김

        String id = SecurityContextHolder.getContext().getAuthentication().getName(); //현재 세선에 로그인된 ID값을 받아옴.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority(); //현재 로그인한 사용자 role 값을 알아낼수있음.

        model.addAttribute("id",id);
        model.addAttribute("role",role);

        return "main";
    }
}
