package com.example.jinder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewHistory {
    @Id
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_who_viewed")
    private UserJinder userWhoViewed;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "viewed_user")
    private UserJinder viewedUser;

//id          bigserial primary key,
//                user_who_viewed bigint references user_jinder(id) unique,
//                viewed_user bigint references user_jinder(id) unique
//                )
}
