package com.hg.events;

import com.hg.Main;
import com.hg.game.Game;
import com.hg.players.GamePlayer;
import com.hg.players.IPlayerTypes;
import com.hg.utils.ContentSenderManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    private Game game;

    public EventListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void joinedServer(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        game.PlayerJoin(e.getPlayer());
    }

    @EventHandler 
    public void leaveServer(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        game.PlayerQuit(e.getPlayer());
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {

        GamePlayer gp = game.getPlayerByID(e.getPlayer().getUniqueId());

        if(game.canDropItem(gp) == false) {
            e.setCancelled(true);
        }
    }

    @EventHandler 
    public void breakBlock(BlockBreakEvent e) {
        if(!game.isMaintenance()) 
            e.setCancelled(true);
    }

    @EventHandler 
    public void blockPlace(BlockPlaceEvent e) {
        if(!game.isMaintenance()) 
            e.setCancelled(true);
    }

    @EventHandler
    public void explodeGrief(EntityExplodeEvent e) {
        e.blockList().clear();
    }   

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);

        GamePlayer gp = game.getPlayerByID(e.getPlayer().getUniqueId());
        String msg = gp.getChatColor() + e.getMessage();

        if(gp.isAlive()) {
            Bukkit.broadcastMessage(gp.getRankFormattedName() + " " + msg);
        } 
        
        else if(gp.isSpectator()) {
            for(GamePlayer spec : game.getPlayersByType(IPlayerTypes.SPECTATOR)) {
                Player p = Bukkit.getPlayer(spec.getID());
                p.sendMessage(ContentSenderManager.ColorMessage("&7&lSPECTATOR ") + gp.getFormattedName() + " " + msg);
            }
        }
        else if(gp.isMutation()) {
            Bukkit.broadcastMessage(ContentSenderManager.ColorMessage("&d&lMUTATION ") + gp.getFormattedName() + " " + msg);
        }
    }

    @EventHandler
    public void food(FoodLevelChangeEvent e) {
        if(!game.inArena())
            e.setCancelled(true);

        else {
            Player p = (Player) e.getEntity();
            GamePlayer gp = game.getPlayerByID(p.getUniqueId());

            if(!gp.isAlive())
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void dmg(EntityDamageEvent e) {
        if(!game.canTakeDamage())
            e.setCancelled(true);
    }
}
