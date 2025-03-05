package com.example.jinder.repository;

import com.example.jinder.entity.Liked;
import com.example.jinder.entity.UserJinder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikedRepository extends JpaRepository<Liked, Long> {

    Optional<Liked> findByWhoLikedAndLiked(UserJinder whoLiked, UserJinder liked);

    @Modifying
    @Query("delete from Liked l where l.liked.nickname = :nickname")
    @Transactional
    void deleteByWhoLiked_Nickname(@Param("nickname") String whoLikedNickname);
}
