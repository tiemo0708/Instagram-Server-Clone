package com.cow.instagram_server_clone.member.controller;

import com.cow.instagram_server_clone.member.dto.request.LoginRequestDto;
import com.cow.instagram_server_clone.member.dto.request.MemberRequestDto;
import com.cow.instagram_server_clone.member.dto.UserDto;
import com.cow.instagram_server_clone.member.dto.response.TokenResponseDto;
import com.cow.instagram_server_clone.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
