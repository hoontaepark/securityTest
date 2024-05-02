package com.example.securityTest.controller;


import com.example.securityTest.dto.JoinDto;
import com.example.securityTest.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService; //간편하게 하기위해 필드 주입을 사용함.

    @GetMapping("/join")
    public String joinP(){
        return "Join";
    }
    @PostMapping("/joinProc")
    public String joinProcess(JoinDto joinDto){

        joinService.joinProcess(joinDto);

        return "redirect:/login"; //로그인 페이지로 리다이렉트


    }

}
