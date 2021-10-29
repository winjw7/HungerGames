package com.hg.utils;

public enum ChatType {
    INFO("&a&l>> &r&8"),
    CAUTION("&e&l>> &r&8"),
    IMPORTANT("&c&l>> &r&8");

    public String seperator;

    private ChatType(String seperator) {
        this.seperator = seperator;
    }
}
