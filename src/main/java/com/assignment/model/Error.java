package com.assignment.model;

import com.assignment.model.enums.CodeError;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model for error returned
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Error error = (Error) o;

        return new EqualsBuilder()
                .append(code, error.code)
                .append(message, error.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(message)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("message", message)
                .toString();
    }
}
