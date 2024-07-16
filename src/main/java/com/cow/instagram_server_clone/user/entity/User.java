package com.cow.instagram_server_clone.user.entity;

import com.cow.instagram_server_clone.post.entity.Comment;
import com.cow.instagram_server_clone.post.entity.Like;
import com.cow.instagram_server_clone.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_or_email", nullable = false, unique = true)
    private String phoneOrEmail;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> posts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Like> likes;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    private Set<UserFollow> following;

    @OneToMany(mappedBy = "followee", fetch = FetchType.LAZY)
    private Set<UserFollow> followers;

    // Getters and Setters
}
