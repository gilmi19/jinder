package com.example.jinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user1")
    private UserJinder user1;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user2")
    private UserJinder user2;
}
