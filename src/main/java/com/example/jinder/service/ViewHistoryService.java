package com.example.jinder.service;

import com.example.jinder.entity.UserJinder;
import com.example.jinder.entity.ViewHistory;
import com.example.jinder.repository.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewHistoryService {
    private final ViewHistoryRepository repository;

    public void add(UserJinder userWhoViewed, UserJinder viewedUser) {
        ViewHistory viewHistory = ViewHistory.builder()
                .userWhoViewed(userWhoViewed)
                .viewedUser(viewedUser)
                .build();
        repository.save(viewHistory);
    }
}
