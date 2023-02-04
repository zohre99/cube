package com.rubic.cube.constant;

public enum CartStatus {
    IN_PROGRESS("In Progress"),
    FINAL("FINAL");
    private final String value;

    private CartStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
