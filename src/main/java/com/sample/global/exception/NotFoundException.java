package com.sample.global.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("존재하지 않습니다.");
    }
}
