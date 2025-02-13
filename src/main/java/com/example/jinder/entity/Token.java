package com.example.jinder.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(max = 6)
    private String token;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserJinder userJinder;
}
