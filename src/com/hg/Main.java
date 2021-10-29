package com.hg;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.hg.events.EventListener;
import com.hg.utils.ContentSenderManager;

public class Main extends JavaPlugin
{
	 public final static String serverName = "&bThe Fridge ";
	 
	 public void onEnable()
	 {
	        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(this), this);
	        
	        Bukkit.getConsoleSender().sendMessage(ContentSenderManager.ColorMessage(serverName) + ChatColor.GREEN + "has been " + ChatColor.BOLD + "ENABLED!");
	        
	        if (!new File(getDataFolder(), "config.yml").exists())
	        {
	            saveDefaultConfig();
	        }
	    }
	
	 public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) 
	 {
        if (cmd.getName().equalsIgnoreCase("hungergames")) 
        {
            
        }

        return false;
    } 
}
