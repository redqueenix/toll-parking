package com.assignment.model;

import com.assignment.model.enums.CodeError;

public class Error {

    private CodeError code;

    private String message;


    public Error(CodeError code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeError getCode() {
        return code;
    }

    public void setCode(CodeError code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
