package com.example.jinder.repository;

import com.example.jinder.entity.Token;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(@Size(max = 6) String tokenValue);

    Optional<Token> findByUserJinder_EmailAndUserJinder_Password(String userEmail, String userPassword);
}
