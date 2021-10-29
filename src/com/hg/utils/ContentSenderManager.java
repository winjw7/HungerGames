package com.hg.utils;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ContentSenderManager {
    public static final String defaultTextColor = "&8";
    
    /**
     * Broadcasts a message to all players
     * @param type The chat type
     * @param msg the msg
     */
    public static void BroadcastMessage(ChatType type, String msg) {
        Bukkit.broadcastMessage(ColorMessage(type.seperator + msg));
    }

    /**
     * Broadcasts a sound to all players
     * @param s the sound
     */
    public static void BroadcastSound(Sound s) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), s, 1, 1);
        }
    }

    /**
     * Sends a group of players a message
     * @param players List of player UUIDs
     * @param type The chat type
     * @param msg the msg
     */
    public static void sendPlayersMessage(List<UUID> players, ChatType type, String msg) {
        for(UUID all : players) {
            Player p = Bukkit.getServer().getPlayer(all);

            if(p.isOnline())
                p.sendMessage(ColorMessage(type.seperator + msg));
        }
    }

    /**
     * Color code a message
     * @param s the msg
     * @return the color coded msg
     */
    public static String ColorMessage(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
