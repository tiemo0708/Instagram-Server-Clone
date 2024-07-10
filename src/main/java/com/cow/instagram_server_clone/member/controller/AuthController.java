package com.cow.instagram_server_clone.member.controller;

import com.cow.instagram_server_clone.member.dto.request.MemberRequestDto;
import com.cow.instagram_server_clone.member.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody MemberRequestDto memberDto) {
        authService.signup(memberDto);
        return "Signup successful";
    }
}
