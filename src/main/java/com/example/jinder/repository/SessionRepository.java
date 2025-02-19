package com.example.jinder.repository;

import com.example.jinder.entity.Session;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    void deleteByToken(@Size(max = 10) String token);

    Optional<Session> findByToken(@Size(max = 22) String token);

    boolean existsByToken(@Size(max = 22) String token);
}
