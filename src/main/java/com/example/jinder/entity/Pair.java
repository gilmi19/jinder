package com.example.jinder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
