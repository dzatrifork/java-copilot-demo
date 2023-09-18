package org.acme.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Profession {
    ACCOUNTANT,
    ARTIST,
    DOCTOR,
    TEACHER;

    Profession fromString(String profession) {
        return Profession.valueOf(profession.toUpperCase());
    }

    @JsonValue
    public String toString() {
        return this.name();
    }
}