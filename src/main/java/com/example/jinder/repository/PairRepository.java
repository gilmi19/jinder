package com.example.jinder.repository;

import com.example.jinder.dto.UserPairsDto;
import com.example.jinder.entity.Pair;
import com.example.jinder.entity.UserJinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {
    //@Param("gender")
//    @Query(nativeQuery = true, value = """
//            select *
//            from user_jinder uj
//                     left join view_history v on uj.id = v.viewed_user
//            where uj.gender = :gender
//              and v.viewed_user is null
//              and uj.is_verified is true
//            limit 1;
//            """)
    @Query(nativeQuery = true, value = """
            select uj.nickname, uj.description, uj.email
            from pair p
            inner join public.user_jinder uj on uj.id = p.user1 or uj.id = p.user2
            where (p.user1 = :userFromSession or p.user2 = :userFromSession)
            and uj.is_verified = true;
            """)
    List<UserPairsDto> findAllByUser(@Param("userFromSession") UserJinder userFromSession);
}
