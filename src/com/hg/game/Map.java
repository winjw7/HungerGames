package com.hg.game;

import java.util.List;

import org.bukkit.Location;

/**
 * A map for a game
 */
public class Map {
    private String name;
    private String desc;
    private List<Location> spawnLocations;
    private List<Location> cases;

    /**
     * Constructor for map
     * @param name the name of the map
     * @param desc the desc of the map
     */
    public Map(String name, String desc, List<Location> spawnLocations, List<Location> cases) {
        this.name = name;
        this.desc = desc;
        this.spawnLocations = spawnLocations;
        this.cases = cases;
    }

    /**
     * Gets the map name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the map description
     * @return desc
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Gets the spawn location of a spot
     * @param index player index
     * @return spawn location
     * @throws Exception
     */
    public Location getSpawnLocation(int index) throws Exception {

        if(index < 0 || index >= spawnLocations.size()) {
            throw new Exception("Invalid spawn location");
        }

        return spawnLocations.get(index);
    }

    /**
     * Get spawn locations for deathmatch
     * @param index player index
     * @param totalPlayers how many players are alive
     * @return spawn loc
     */
    public Location getDeathmatchSpawn(int index, int totalPlayers) {
        int multiplier = Math.floorDiv(Game.MAX_PLAYERS, totalPlayers);
        int spot = index * multiplier;
        
        try {
            return getSpawnLocation(spot);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fills cases
     */
    public void fillCases() {

    }
}
