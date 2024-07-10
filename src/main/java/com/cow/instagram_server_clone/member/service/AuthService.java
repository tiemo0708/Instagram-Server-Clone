package com.cow.instagram_server_clone.member.service;

import com.cow.instagram_server_clone.member.dto.request.MemberRequestDto;
import com.cow.instagram_server_clone.member.entity.Member;
import com.cow.instagram_server_clone.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(MemberRequestDto memberDto) {
        if (memberRepository.findByUsername(memberDto.getUsername()).isPresent()) {
            throw new RuntimeException("이미 가입된 유저입니다.");
        }

        Member member = new Member();
        member.setUsername(memberDto.getUsername());
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());

        memberRepository.save(member);
    }
}
