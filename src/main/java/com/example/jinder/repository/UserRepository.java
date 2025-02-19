package com.example.jinder.repository;

import com.example.jinder.entity.UserJinder;
import com.example.jinder.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserJinder, Long> {
    Optional<UserJinder> findByNicknameOrEmail(String nickname, String email);

    Optional<UserJinder> findByEmailAndPassword(String nickname, String password);

    Optional<UserJinder> findByEmail(String email);

    Optional<UserJinder> findByNicknameAndPassword(String nickname, String password);

    @Query(nativeQuery = true, value = """
            select *
            from user_jinder uj
                     left join view_history v on uj.id = v.viewed_user
            where uj.gender = :gender
              and v.viewed_user is null
              and uj.is_verified is true
            limit 1;
            """)
    Optional<UserJinder> findUnviewedUser(@Param("gender") Gender gender);

    Optional<UserJinder> findByNickname(String userName);

    void deleteByNickname(String nickname);
}
