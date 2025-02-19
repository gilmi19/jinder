package com.example.jinder.service;

import com.example.jinder.entity.Liked;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.exception.LikedException;
import com.example.jinder.repository.LikedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikedService {
    private final LikedRepository likedRepository;

    public void save(UserJinder whoLiked, UserJinder likedUser) {
        Liked entityLiked = Liked.builder()
                .whoLiked(whoLiked)
                .liked(likedUser)
                .build();
        likedRepository.save(entityLiked);
    }

    public UserJinder findByWhoLikedAndLiked(UserJinder whoLikedUser, UserJinder likedUser) {
        Liked liked = likedRepository.findByWhoLikedAndLiked(whoLikedUser, likedUser)
                .orElseThrow(() -> new LikedException("Этот пользователь не лайкнул вас"));
        return liked.getWhoLiked();
    }

    public boolean checkMathes(UserJinder whoLikedUser, UserJinder likedUser) {
        return likedRepository.findByWhoLikedAndLiked(whoLikedUser, likedUser).isPresent();
    }
}
