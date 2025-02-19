package com.example.jinder.service;

import com.example.jinder.dto.UserPairsDto;
import com.example.jinder.entity.Pair;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.repository.PairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PairService {
    private final PairRepository pairRepository;

    public void create(UserJinder user1, UserJinder user2) {
        var pair = Pair.builder()
                .user1(user1)
                .user2(user2)
                .build();
        pairRepository.save(pair);
    }

    public List<UserPairsDto> findAllByUser(UserJinder userFromSession) {
        return pairRepository.findAllByUser(userFromSession);
    }
}
