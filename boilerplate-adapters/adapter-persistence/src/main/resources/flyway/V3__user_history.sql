DROP TABLE IF EXISTS `netplix`.`user_histories`;
CREATE TABLE `netplix`.`user_histories`
(
    USER_HISTORY_ID BIGINT       NOT NULL AUTO_INCREMENT COMMENT '사용자 이력 ID',
    USER_ID         VARCHAR(255) NOT NULL COMMENT '사용자 ID',
    USER_ROLE       VARCHAR(255) NOT NULL COMMENT '사용자 역할',
    REQ_IP          VARCHAR(255) NOT NULL COMMENT '요청 IP',
    REQ_METHOD      VARCHAR(255) NOT NULL COMMENT '요청 메소드',
    REQ_URL         VARCHAR(255) NOT NULL COMMENT '요청 URL',
    REQ_HEADER      TEXT         NOT NULL COMMENT '요청 헤더',
    REQ_PAYLOAD     TEXT         NOT NULL COMMENT '요청 바디',

    CREATED_AT      DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY      VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT     DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY     VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (USER_HISTORY_ID)
);