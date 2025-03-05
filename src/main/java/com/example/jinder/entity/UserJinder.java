package com.example.jinder.entity;

import com.example.jinder.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJinder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(max = 50)
    private String email;

    @Column
    private String password;

    @Column(unique = true)
    private String nickname;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column
    @Size(max = 250)
    private String description;

    @Column()
    private Boolean isVerified;

    @OneToMany(mappedBy = "whoLiked", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Liked> whoLikeds;

    @OneToMany(mappedBy = "liked", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Liked> likeds;

    @OneToMany(mappedBy = "user1", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Pair> user1;

    @OneToMany(mappedBy = "user2", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Pair> user2;

    @OneToMany(mappedBy = "userWhoViewed", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ViewHistory> usersWhoViewed;

    @OneToMany(mappedBy = "viewedUser", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ViewHistory> viewedUsers;

    @OneToMany(mappedBy = "userJinder", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Token> tokens;

    @OneToMany(mappedBy = "userJinder", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Session> sessions;
}