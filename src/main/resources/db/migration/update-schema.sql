CREATE TABLE liked
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    who_liked BIGINT,
    liked     BIGINT,
    CONSTRAINT pk_liked PRIMARY KEY (id)
);

CREATE TABLE pair
(
    id    BIGINT NOT NULL,
    user1 BIGINT,
    user2 BIGINT,
    CONSTRAINT pk_pair PRIMARY KEY (id)
);

CREATE TABLE token
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    token           VARCHAR(6),
    expiration_date TIMESTAMP WITHOUT TIME ZONE,
    user_id         BIGINT,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user_jinder
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    email       VARCHAR(50),
    password    VARCHAR(255),
    nickname    VARCHAR(255),
    gender      VARCHAR(255),
    description VARCHAR(250),
    is_verified BOOLEAN,
    CONSTRAINT pk_userjinder PRIMARY KEY (id)
);

CREATE TABLE view_history
(
    id              BIGINT NOT NULL,
    user_who_viewed BIGINT,
    viewed_user     BIGINT,
    CONSTRAINT pk_viewhistory PRIMARY KEY (id)
);

ALTER TABLE user_jinder
    ADD CONSTRAINT uc_userjinder_email UNIQUE (email);

ALTER TABLE user_jinder
    ADD CONSTRAINT uc_userjinder_nickname UNIQUE (nickname);

ALTER TABLE liked
    ADD CONSTRAINT FK_LIKED_ON_LIKED FOREIGN KEY (liked) REFERENCES user_jinder (id);

ALTER TABLE liked
    ADD CONSTRAINT FK_LIKED_ON_WHO_LIKED FOREIGN KEY (who_liked) REFERENCES user_jinder (id);

ALTER TABLE pair
    ADD CONSTRAINT FK_PAIR_ON_USER1 FOREIGN KEY (user1) REFERENCES user_jinder (id);

ALTER TABLE pair
    ADD CONSTRAINT FK_PAIR_ON_USER2 FOREIGN KEY (user2) REFERENCES user_jinder (id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user_jinder (id);

ALTER TABLE view_history
    ADD CONSTRAINT FK_VIEWHISTORY_ON_USER_WHO_VIEWED FOREIGN KEY (user_who_viewed) REFERENCES user_jinder (id);

ALTER TABLE view_history
    ADD CONSTRAINT FK_VIEWHISTORY_ON_VIEWED_USER FOREIGN KEY (viewed_user) REFERENCES user_jinder (id);
CREATE TABLE liked
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    who_liked BIGINT,
    liked     BIGINT,
    CONSTRAINT pk_liked PRIMARY KEY (id)
);

CREATE TABLE pair
(
    id    BIGINT NOT NULL,
    user1 BIGINT,
    user2 BIGINT,
    CONSTRAINT pk_pair PRIMARY KEY (id)
);

CREATE TABLE token
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    token           VARCHAR(6),
    expiration_date TIMESTAMP WITHOUT TIME ZONE,
    user_id         BIGINT,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user_jinder
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    email       VARCHAR(50),
    password    VARCHAR(255),
    nickname    VARCHAR(255),
    gender      VARCHAR(255),
    description VARCHAR(250),
    is_verified BOOLEAN,
    CONSTRAINT pk_userjinder PRIMARY KEY (id)
);

CREATE TABLE view_history
(
    id              BIGINT NOT NULL,
    user_who_viewed BIGINT,
    viewed_user     BIGINT,
    CONSTRAINT pk_viewhistory PRIMARY KEY (id)
);

ALTER TABLE user_jinder
    ADD CONSTRAINT uc_userjinder_email UNIQUE (email);

ALTER TABLE user_jinder
    ADD CONSTRAINT uc_userjinder_nickname UNIQUE (nickname);

ALTER TABLE liked
    ADD CONSTRAINT FK_LIKED_ON_LIKED FOREIGN KEY (liked) REFERENCES user_jinder (id);

ALTER TABLE liked
    ADD CONSTRAINT FK_LIKED_ON_WHO_LIKED FOREIGN KEY (who_liked) REFERENCES user_jinder (id);

ALTER TABLE pair
    ADD CONSTRAINT FK_PAIR_ON_USER1 FOREIGN KEY (user1) REFERENCES user_jinder (id);

ALTER TABLE pair
    ADD CONSTRAINT FK_PAIR_ON_USER2 FOREIGN KEY (user2) REFERENCES user_jinder (id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user_jinder (id);

ALTER TABLE view_history
    ADD CONSTRAINT FK_VIEWHISTORY_ON_USER_WHO_VIEWED FOREIGN KEY (user_who_viewed) REFERENCES user_jinder (id);

ALTER TABLE view_history
    ADD CONSTRAINT FK_VIEWHISTORY_ON_VIEWED_USER FOREIGN KEY (viewed_user) REFERENCES user_jinder (id);