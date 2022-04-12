package com.stackroute.resourcesservice.domain;

public enum Ratings {
    POOR(1),NOT_BAD(2),GOOD(3),VERY_GOOD(4),EXCELLENT(5);
    private int value;
    Ratings(int value) {
        this.value = value;
    }
}
