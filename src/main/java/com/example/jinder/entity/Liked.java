package com.example.jinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "liked")
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
