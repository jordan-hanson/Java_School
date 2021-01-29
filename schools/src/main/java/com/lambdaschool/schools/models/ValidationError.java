package com.lambdaschool.schools.models;

public class ValidationError {
    private String fieldName;
    private String message;

    public ValidationError() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
//    @Override
//    public String toString()
//    {
//        return "ValidationError{" + "Code='" + Code + '\'' + ", message='" + message + '\'' + '}';
//    }
}
