package com.example.jinder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "who_liked")
    private UserJinder whoLiked;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "liked")
    private UserJinder liked;
}
