package com.hg.items;

import org.bukkit.ChatColor;

public enum ItemRarity {
    COMMON(75, ChatColor.RESET),
    UNCOMMON(15, ChatColor.GREEN),
    RARE(6, ChatColor.AQUA),
    VERY_RARE(3, ChatColor.DARK_PURPLE),
    LEGENDARY(1, ChatColor.RED);

    public final int chance;
    public ChatColor color;


    private ItemRarity(int chance, ChatColor color) {
        this.chance = chance;
        this.color = color;
    }
}
