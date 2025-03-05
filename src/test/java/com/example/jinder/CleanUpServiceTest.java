package com.example.jinder;

import com.example.jinder.repository.LikedRepository;
import com.example.jinder.repository.SessionRepository;
import com.example.jinder.repository.TokenRepository;
import com.example.jinder.repository.UserRepository;
import com.example.jinder.service.AuthenticationService;
import com.example.jinder.service.AuthorizationService;
import com.example.jinder.service.TokenService;
import com.example.jinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CleanUpServiceTest {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    LikedRepository likedRepository;
    public void cleanUp(String nickname){
        sessionRepository.deleteByUserJinder_Nickname(nickname);
        tokenRepository.deleteByUserJinder_Nickname(nickname);
        userRepository.deleteByNickname(nickname);
        likedRepository.deleteByWhoLiked_Nickname("Amir");
        likedRepository.deleteByWhoLiked_Nickname("Inna");
    }
}
