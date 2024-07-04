package com.cow.instagram_server_clone.member.util;

import com.cow.instagram_server_clone.member.jwt.JwtTokenProvider;
import com.cow.instagram_server_clone.member.entity.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final JwtTokenProvider jwtTokenProvider;

    public String createToken(String username, Set<Authority> authorities) {
        return jwtTokenProvider.createToken(username, authorities);
    }
}
