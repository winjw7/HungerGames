package com.hg.mutations;

import java.util.List;
import java.util.UUID;

import com.hg.players.GamePlayer;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public abstract class Mutatation {
   
    private String name;
    private EntityType type;
    private int maxHealth;
    private GamePlayer target;

    public Mutatation(EntityType type, String name, int max, GamePlayer target) {
        this.type = type;
        this.name = name;
        this.maxHealth = max;
        this.target = target;
    }

    public void applyEffects(Player p) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        p.setHealth(maxHealth);
        
        PlayerInventory inv = p.getInventory();
        
        inv.clear();
        
        inv.setHelmet(getHelmet());
        inv.setChestplate(getChestplate());
        inv.setLeggings(getLeggings());
        inv.setBoots(getBoots());

        for(ItemStack item : getItems()) {
            inv.addItem(item);
        }

    }

    public String getName() {
        return name;
    }

    public EntityType getType() {
        return type;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public UUID getTargetID() {
        return target.getID();
    }

    public abstract List<PotionEffect> getEffects();

    public abstract List<ItemStack> getItems();
    public abstract ItemStack getHelmet();
    public abstract ItemStack getChestplate();
    public abstract ItemStack getLeggings();
    public abstract ItemStack getBoots();
   
}