package org.acme.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Profession {
    ACCOUNTANT,
    ARTIST,
    ATHLETE,
    AUTHOR,
    BAKER,
    BUILDER,
    CHEF,
    DENTIST,
    DESIGNER,
    DEVELOPER,
    DOCTOR,
    DRIVER,
    ENGINEER,
    FIREFIGHTER,
    JOURNALIST,
    JUDGE,
    LAWYER,
    NURSE,
    PILOT,
    POLICE,
    POLITICIAN,
    PROGRAMMER,
    PSYCHOLOGIST,
    RECEPTIONIST,
    SALESPERSON,
    SCIENTIST,
    SECRETARY,
    SOLDIER,
    STUDENT,
    SURGEON,
    TEACHER,
    WRITER;

    Profession fromString(String profession) {
        return Profession.valueOf(profession.toUpperCase());
    }

    @JsonValue
    public String toString() {
        return this.name();
    }
}