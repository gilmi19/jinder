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
public class Pair {
    @Id
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user1")
    private UserJinder user1;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user2")
    private UserJinder user2;

//    id          bigserial primary key,
//    user1 bigint references user_jinder(id) unique,
//    user2 bigint references user_jinder(id) unique
//                );
}
