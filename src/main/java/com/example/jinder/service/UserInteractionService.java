package com.example.jinder.service;

import com.example.jinder.dto.UserPairsDto;
import com.example.jinder.dto.UserShowDto;
import com.example.jinder.entity.Session;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.enums.Gender;
import com.example.jinder.exception.LikedException;
import com.example.jinder.mapper.UserJinderMapper;
import com.example.jinder.repository.LikedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInteractionService {
    private final ViewHistoryService viewHistoryService;
    private final UserService userService;
    private final SessionService sessionService;
    @Qualifier("userJinderMapper")
    private final UserJinderMapper mapper;
    private final LikedService likedService;
    private final PairService pairService;
    private final LikedRepository likedRepository;

    /**
     * @param sessionToken токен сессии пользователя
     * @return Возвращает 1 пользователя (nickname, интересы) противоположного пола из списка зарегистрированных
     * пользователей,
     * 3.3.2 Заносит в историю просмотров показанного пользователя (nickname, описание интересов).
     */
    @Transactional
    public UserShowDto show(String sessionToken) {
        Session entitySessionToken = sessionService.findByToken(sessionToken);
        log.info("найденная сессия - {}", entitySessionToken);

        UserJinder userWhoViewed = entitySessionToken.getUserJinder();

        return switch (userWhoViewed.getGender()) {
            case MALE -> showUserByGender(userWhoViewed, Gender.FEMALE);
            case FEMALE -> showUserByGender(userWhoViewed, Gender.MALE);
        };
    }

    /**
     * @param sessionToken токен сессии пользователя
     * @param nickname
     * @param isSecondUser проверка на то ставит ли лайк пользователь, которого лайкнули
     *                     пользователя, который понравился.
     *                     Добавляет новую запись, нравится: текущий пользователь, пользователь который понравился
     *                     Если пользователь, который понравился, лайкнул текущего пользователя, то добавляется пара.
     */
    @Transactional
    public void like(String sessionToken, String nickname, boolean isSecondUser) {

        Session userSession = sessionService.findByToken(sessionToken);

        UserJinder whoLiked = userSession.getUserJinder();
        UserJinder likedUser = userService.findByNickname(nickname);
        likedService.save(whoLiked, likedUser);

        if (isSecondUser) {
            createPair(whoLiked, likedUser);
        }
    }

    public List<UserPairsDto> getPairs(String sessionToken) {
        UserJinder userFromSession = sessionService.findByToken(sessionToken).getUserJinder();
        return pairService.findAllByUser(userFromSession);
    }

    private void createPair(UserJinder whoLiked, UserJinder liked) {
        if (!likedService.checkMathes(liked, whoLiked)) {
            throw new LikedException("Этот пользователь не лайкнул вас");
        }
        pairService.create(whoLiked, liked);
    }

    private UserShowDto showUserByGender(UserJinder userWhoViewed, Gender gender) {
        log.info("Переданный пол - {}", gender);
        UserJinder viewedUser = userService.findUnviewedUser(gender.name());
        viewHistoryService.add(userWhoViewed, viewedUser);
        return mapper.toShowDto(viewedUser);
    }

    //TODO: уведомление о том что лайкнули
}
