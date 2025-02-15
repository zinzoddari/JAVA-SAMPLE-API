package com.sample.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice
class RootExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SampleBusinessException.class)
    protected ErrorResponse businessExceptionHandler(SampleBusinessException e) {
        log.error("[ SampleBusinessException ] : ", e);
        return createMessage(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    protected ErrorResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("[ RuntimeException ] : ", e);
        return createMessage(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorResponse exceptionHandler(Exception e) {
        log.error("[ Exception ] : ", e);
        return createMessage(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("[ 400 VALIDATE ERROR ] : ", ex);

        final BindingResult bindingResult = ex.getBindingResult();

        final List<InvalidResponse> responses = bindingResult.getFieldErrors().stream()
                .map(fieldError -> new InvalidResponse(fieldError.getField()
                        , fieldError.getDefaultMessage()
                        , fieldError.getRejectedValue())
                ).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        problemDetail.setProperty("parameters", responses);

        return handleExceptionInternal(ex, problemDetail, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorResponse createMessage(HttpStatus httpStatus, Exception e) {
        return ErrorResponse.builder(e, httpStatus, e.getMessage())
                .title(httpStatus.getReasonPhrase())
                .build();
    }
}
