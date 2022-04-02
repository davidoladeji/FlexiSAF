package com.davidoladeji.flexisaf.data;

public enum CreatorEnum {
    CREATOR_ONE("Mr. Adepetu"), CREATOR_TWO("Miss Idowu"), CREATOR_THREE("Prof. Soyinka");

    private final String text;

    CreatorEnum(final String text) {
        this.text = text;
    }



    @Override
    public String toString() {
        return text;
    }
}
