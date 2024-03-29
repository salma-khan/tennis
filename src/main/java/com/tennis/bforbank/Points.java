package com.tennis.bforbank;

public enum Points {
    _0("0"), _15("15"), _30("30"), _40("40");

    private final String name;

    Points(String name) {
        this.name = name;
    }

    public static Points from(int value) {
        return Points.values()[value];
    }

    public String getName() {
        return name;
    }
}
