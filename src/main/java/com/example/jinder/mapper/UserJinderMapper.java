package com.example.jinder.mapper;

import com.example.jinder.dto.SignUpDto;
import com.example.jinder.dto.UserShowDto;
import com.example.jinder.entity.UserJinder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserJinderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "whoLikeds", ignore = true)
    @Mapping(target = "likeds", ignore = true)
    @Mapping(target = "user1", ignore = true)
    @Mapping(target = "user2", ignore = true)
    @Mapping(target = "usersWhoViewed", ignore = true)
    @Mapping(target = "viewedUsers", ignore = true)
    @Mapping(target = "isVerified", constant = "false")
    UserJinder toEntity(SignUpDto dto);

    UserShowDto toShowDto(UserJinder userJinder);
}
