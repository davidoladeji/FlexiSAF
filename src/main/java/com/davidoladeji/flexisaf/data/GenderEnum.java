package com.davidoladeji.flexisaf.data;

public enum GenderEnum {
    GENDER_ONE("M"), GENDER_TWO("F");

    private final String text;

    GenderEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
