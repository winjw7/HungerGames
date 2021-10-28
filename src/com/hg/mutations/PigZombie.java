package com.hg.mutations;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PigZombie extends Mutatation {

    public PigZombie() {
        super(EntityType.PIG_ZOMBIE, "Zombie Pigman", 20);
    }

    @Override
    public List<PotionEffect> getEffects() {
        List<PotionEffect> effects = new ArrayList<PotionEffect>();
        effects.add(new PotionEffect(PotionEffectType.SPEED, 99999999, 1));
        return effects;
    }

    @Override
    public List<ItemStack> getItems() {
        List<ItemStack> items = new ArrayList<ItemStack>();

        items.add(new ItemStack(Material.GOLDEN_SWORD));
        items.add(new ItemStack(Material.COMPASS));

        return items;
    }

    @Override
    public ItemStack getHelmet() {
        return new ItemStack(Material.LEATHER_HELMET);
    }

    @Override
    public ItemStack getChestplate() {
        return new ItemStack(Material.LEATHER_CHESTPLATE);
    }

    @Override
    public ItemStack getLeggings() {
        return new ItemStack(Material.LEATHER_LEGGINGS);
    }

    @Override
    public ItemStack getBoots() {
        return new ItemStack(Material.LEATHER_BOOTS);
    }
    
}
