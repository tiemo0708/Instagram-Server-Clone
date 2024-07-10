package com.cow.instagram_server_clone.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String username;
    private String password;
    private String email;
    private String name;
}
