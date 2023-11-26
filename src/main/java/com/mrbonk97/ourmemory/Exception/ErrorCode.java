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
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 유효하지 않음"),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이메일이 중복됨."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "권한이 없음"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 에러");

    private final HttpStatus status;
    private final String message;
}