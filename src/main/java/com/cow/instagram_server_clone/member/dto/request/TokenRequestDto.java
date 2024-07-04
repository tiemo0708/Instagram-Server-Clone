package com.cow.instagram_server_clone.member.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequestDto {
    private String token;
}