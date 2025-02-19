package com.example.jinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_who_viewed")
    private UserJinder userWhoViewed;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "viewed_user")
    private UserJinder viewedUser;
}
