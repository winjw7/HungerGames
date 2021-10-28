package com.hg.players;

import java.util.ArrayList;
import java.util.List;

import com.hg.stats.*;

public class PlayerGameStats {
    private List<GameStat<?>> stats;

    /**
     * A list of player stats
     * @param type the player type
     */
    public PlayerGameStats(IPlayerTypes type) {
        stats = new ArrayList<GameStat<?>>();
       
        try {
            stats.add(new Kills());
            stats.add(new WasAlive(type));
            stats.add(new HasMutated());
            stats.add(new HasSponsored(type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a stat value
     * @param type The stat type
     * @return the value
     */
    public Object getStatValue(GameStatTypes type) {
        GameStat<?> stat = stats.stream().filter((s) -> s.getStatType() == type).findFirst().orElse(null);
        
        return stat != null ? stat.getValue() : null;
    }
}
