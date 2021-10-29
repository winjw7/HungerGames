package com.hg.utils;

public enum ChatType {
    INFO("&a&l >> &r&7"),
    CAUTION("&e&l >> &r&7"),
    IMPORTANT("&c&l >> &r&7");

    public String seperator;

    private ChatType(String seperator) {
        this.seperator = seperator;
    }
}
