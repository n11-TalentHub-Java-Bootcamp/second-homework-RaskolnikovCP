package com.ozansaribal.n11_bootcamp_week2_trial.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundByMember extends RuntimeException {

    public CommentNotFoundByMember(String message) {
        super(message);
    }

}