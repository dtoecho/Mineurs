package com.newlecmineursprj.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResult extends Exception{
    private Exception exception;
    private String defaultMessage;
    private String extendMessage;
    private Integer code;
    private String viewName;
    /**
     * 생성자 메서드를 오버라이딩해서 사용가능
     * null 및 불필요한 인자 넣어서 생성하지 않기
     */
    public ErrorResult(Exception exception, String defaultMessage) {
        this.exception = exception;
        this.defaultMessage = defaultMessage;
    }
}
