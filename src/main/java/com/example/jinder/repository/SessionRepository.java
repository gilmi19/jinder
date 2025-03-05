package com.example.jinder.repository;

import com.example.jinder.entity.Session;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Modifying
    @Query("delete from Session t where t.token = :token")
    @Transactional
    void deleteByToken(@Size(max = 10) @Param("token") String token);

    Optional<Session> findByToken(@Size(max = 22) String token);

    boolean existsByToken(@Size(max = 22) String token);

    Optional<Session> findByUserJinder_Nickname(String nickname);

    @Modifying(clearAutomatically = true)
    @Query("delete from Session t where t.userJinder.nickname = :nickname")
    @Transactional
    void deleteByUserJinder_Nickname(@Param("nickname") String nickname);
}
