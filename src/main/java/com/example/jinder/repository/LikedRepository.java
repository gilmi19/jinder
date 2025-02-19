package com.example.jinder.repository;

import com.example.jinder.entity.Liked;
import com.example.jinder.entity.UserJinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikedRepository extends JpaRepository<Liked, Long> {

    Optional<Liked> findByWhoLikedAndLiked(UserJinder whoLiked, UserJinder liked);
}
