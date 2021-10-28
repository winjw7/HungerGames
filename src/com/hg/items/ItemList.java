package com.hg.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A list of items that are in the game
 */
public class ItemList {

    private List<Item> items;
    private List<ItemStack> weightedList;

    public ItemList() {
        items = new ArrayList<Item>();

        items.add(new Item(ItemRarity.COMMON, Material.WOODEN_AXE));
        items.add(new Item(ItemRarity.RARE, Material.IRON_AXE));


        CreateWeighedList();
    }

    private void CreateWeighedList() {
        weightedList = new ArrayList<ItemStack>();

        for(Item i : items) {
            ItemStack item = i.getItem();

            for(int j = 0; j < i.getWeightedAmount(); j++) {
                weightedList.add(item);
            }
        }
    }
    
    /**
     * Gets a random item from the weighted list
     * @return random item
     */
    public ItemStack getRandomItem() {
        int max = weightedList.size();
        int index = new Random().nextInt(max);

        return weightedList.get(index);
    }
}
