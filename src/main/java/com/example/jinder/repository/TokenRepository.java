package com.example.jinder.repository;

import com.example.jinder.entity.Token;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(@Size(max = 6) String tokenValue);

    Optional<Token> findByUserJinder_EmailAndUserJinder_Password(String userEmail, String userPassword);

    void deleteByUserJinder_Email(@Size(max = 50) String userJinderEmail);

    Optional<Token> findFirstByOrderByCreatedAtDesc();

    @Transactional
    @Modifying()
    @Query("delete from Token t where t.userJinder.nickname = :nickname")
    void deleteByUserJinder_Nickname(@Param("nickname") String nickname);

    Optional<Token> findByUserJinder_Nickname(String userNickname);
}
