package com.org.cts.edusmart_backend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CourseStatus {
    PENDING,APPROVED,REJECTED;

    @JsonCreator
    public static CourseStatus fromString(String value) {
        return CourseStatus.valueOf(value.toUpperCase());
    }
}
