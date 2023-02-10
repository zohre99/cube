package com.rubic.cube.constant;

public enum CartStatus {
    PROGRESSING("PROGRESSING"),
    SUBMITTED("SUBMITTED");
    private final String value;

    CartStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
