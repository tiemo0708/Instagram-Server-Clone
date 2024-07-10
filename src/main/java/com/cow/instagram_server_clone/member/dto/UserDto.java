package com.cow.instagram_server_clone.member.dto;

import com.cow.instagram_server_clone.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// UserDto.java
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private boolean activated;

    public static UserDto from(Member member) {
        return UserDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .name(member.getName())
                .activated(member.isActivated())
                .build();
    }

    @Builder
    public UserDto(Long id, String username, String email, String name, boolean activated) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.activated = activated;
    }

    // getter, setter 추가
}
