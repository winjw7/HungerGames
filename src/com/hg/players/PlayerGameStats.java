package com.hg.players;

import java.util.HashMap;
import java.util.Map;

import com.hg.stats.*;

public class PlayerGameStats {
    private Map<GameStatTypes, Object> stats;

    /**
     * A list of player stats
     * @param type the player type
     */
    public PlayerGameStats(IPlayerTypes type) {
        stats = new HashMap<GameStatTypes, Object>();
       
        stats.put(GameStatTypes.KILLS, 0);
        stats.put(GameStatTypes.HAS_MUTATED, false);
        stats.put(GameStatTypes.HAS_SPONSORED, false);
        stats.put(GameStatTypes.WAS_ALIVE, type == IPlayerTypes.TRIBUTE);
    }

    /**
     * Gets a stat value
     * @param type The stat type
     * @return the value
     */
    public Object getStatValue(GameStatTypes type) {

        if(!stats.containsKey(type))
            return null;

        return stats.get(type);
    }

    /**
     * Sets a stat value
     * @param type the stat type
     * @param value the value
     */
    public void setStatValue(GameStatTypes type, Object value) {
        stats.put(type, value);
    }
}
