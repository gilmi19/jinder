package com.example.jinder.repository;

import com.example.jinder.entity.UserJinder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJinder, Long> {
    Optional<UserJinder> findByNicknameOrEmail(String nickname, String email);

    Optional<UserJinder> findByEmailAndPassword(String nickname, String password);
}
