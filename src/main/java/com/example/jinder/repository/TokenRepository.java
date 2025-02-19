package com.example.jinder.repository;

import com.example.jinder.entity.Token;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(@Size(max = 6) String tokenValue);

    Optional<Token> findByUserJinder_EmailAndUserJinder_Password(String userEmail, String userPassword);

    void deleteByUserJinder_Email(@Size(max = 50) String userJinderEmail);
}
