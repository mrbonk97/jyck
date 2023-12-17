package com.mrbonk97.ourmemory.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않음."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음."),
    FRIEND_NOT_FOUND(HttpStatus.NOT_FOUND, "친구를 찾을 수 없음."),
    FRIEND_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "친구 그룹을 찾을 수 없음."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 유효하지 않음"),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이메일이 중복됨."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "권한이 없음"),
    UNABLE_TO_SEND_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 발송 실패"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 에러"),
    OAUTH2USER_ERROR(HttpStatus.FORBIDDEN, "아직 지원하지 않은 기능"),
    DUPLICATED_OAUTH2USER(HttpStatus.CONFLICT, "OAuth2 유저가 중복됨"),
    REQUEST_EXPIRED(HttpStatus.BAD_REQUEST, "요청 코드가 유효하지 않거나 만료되었음"),
    OAUTH2USER_NOT_FOUND(HttpStatus.NOT_FOUND, "OAuth2 데이터 베이스에 유저 없음");

    private final HttpStatus status;
    private final String message;
}