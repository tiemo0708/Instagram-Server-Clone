package com.cow.instagram_server_clone.member.service;

import com.cow.instagram_server_clone.member.dto.request.LoginRequestDto;
import com.cow.instagram_server_clone.member.dto.request.MemberRequestDto;
import com.cow.instagram_server_clone.member.dto.response.TokenResponseDto;
import com.cow.instagram_server_clone.member.dto.UserDto;
import com.cow.instagram_server_clone.member.entity.Authority;
import com.cow.instagram_server_clone.member.entity.Member;
import com.cow.instagram_server_clone.member.repository.MemberRepository;
import com.cow.instagram_server_clone.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    @Transactional
    public UserDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.findByUsername(memberRequestDto.getUsername()).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        Member member = Member.builder()
                .email(memberRequestDto.getEmail())
                .username(memberRequestDto.getUsername())
                .name(memberRequestDto.getName())
                .password(passwordEncoder.encode(memberRequestDto.getPassword()))
                .authorities(Collections.singleton(Authority.builder().name("ROLE_USER").build()))
                .build();

        memberRepository.save(member);

        return UserDto.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .name(member.getName())
                .build();
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<Member> memberOptional = memberRepository.findByUsername(loginRequestDto.getUsername());
        if (!memberOptional.isPresent()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        Member member = memberOptional.get();
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String token = securityUtil.createToken(member.getUsername(), member.getAuthorities());
        return new TokenResponseDto(token);
    }
}
