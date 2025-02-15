package com.sample.global.exception;

public class SampleBusinessException extends RuntimeException {

    public SampleBusinessException() {
        super("알 수 없는 오류 입니다.");
    }

    public SampleBusinessException(final String message) {
        super(message);
    }
}
