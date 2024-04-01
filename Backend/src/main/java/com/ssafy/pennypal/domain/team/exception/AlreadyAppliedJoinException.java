package com.ssafy.pennypal.domain.team.exception;

public class AlreadyAppliedJoinException extends RuntimeException {
    public AlreadyAppliedJoinException(String message) {
        super(message);
    }
}