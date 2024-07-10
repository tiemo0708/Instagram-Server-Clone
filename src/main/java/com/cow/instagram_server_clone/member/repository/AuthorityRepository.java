package com.cow.instagram_server_clone.member.repository;

import com.cow.instagram_server_clone.member.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
