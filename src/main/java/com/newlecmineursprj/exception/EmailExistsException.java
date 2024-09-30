package com.newlecmineursprj.exception;

public class EmailExistsException extends RuntimeException {

    private String email;

    public EmailExistsException(String email) {
        super("이미 가입된 이메일입니다: "+email);
        this.email = email;
    }
}
