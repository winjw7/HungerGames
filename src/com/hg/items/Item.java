package com.hg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
    private Material type;
    private ItemRarity rarity;
    private String name;
    private int amount;

    /**
     * An item that can be dropped
     * @param rarity the rarity of an item
     * @param type the item material
     * @param amount the amount of the item
     */
    public Item(ItemRarity rarity, Material type, int amount) {
        this.type = type;
        this.rarity = rarity;
        this.name = rarity.color + type.toString();
        this.amount = amount;
    }

    /**
     * An item that can be dropped
     * @param rarity the rarity of an item
     * @param type the item material
     * @param name custom name
     * @param amount the amount of the item
     */
    public Item(ItemRarity rarity, Material type, String name, int amount) {
        this.type = type;
        this.rarity = rarity;
        this.name = rarity.color + name;
        this.amount = amount;
    }

     /**
     * An item that can be dropped
     * @param rarity the rarity of an item
     * @param type the item material
     * @param amount the amount of the item
     */
    public Item(ItemRarity rarity, Material type) {
        this.type = type;
        this.rarity = rarity;
        this.name = rarity.color + type.toString();
        this.amount = 1;
    }

    /**
     * An item that can be dropped
     * @param rarity the rarity of an item
     * @param type the item material
     * @param name custom name
     * @param amount the amount of the item
     */
    public Item(ItemRarity rarity, Material type, String name) {
        this.type = type;
        this.rarity = rarity;
        this.name = rarity.color + name;
        this.amount = 1;
    }

    /**
     * Gets the item rarity
     * @return rarity
     */
    public ItemRarity getRarity() {
        return rarity;
    }

    /**
     * Creates the item
     * @return item
     */
    public ItemStack getItem() {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Get how many items to add in a weighted list
     * @return amount
     */
    public int getWeightedAmount() {
        return rarity.chance;
    }
}
