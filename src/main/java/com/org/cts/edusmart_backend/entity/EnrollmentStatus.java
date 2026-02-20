package com.org.cts.edusmart_backend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnrollmentStatus {
    PENDING, APPROVED, REJECTED;

    @JsonCreator
    public static EnrollmentStatus fromString(String value) {
        return EnrollmentStatus.valueOf(value.toUpperCase());
    }
}