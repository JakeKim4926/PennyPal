package com.ssafy.pennypal.domain.team.exception;

public class BannedMemberJoinException extends RuntimeException {
    public BannedMemberJoinException(String message) {
        super(message);
    }
}
