package com.hg.stats;

import java.util.HashMap;
import java.util.Map;

import com.hg.players.IPlayerTypes;

public class WasAlive extends GameStat<Boolean> {

    public WasAlive(IPlayerTypes type) throws Exception {
        super(GameStatTypes.WAS_ALIVE, type, defaultValues());
    }

    public static Map<IPlayerTypes, Boolean> defaultValues() {

        Map<IPlayerTypes, Boolean> defaults = new HashMap<IPlayerTypes, Boolean>();

        defaults.put(IPlayerTypes.TRIBUTE, true);
        defaults.put(IPlayerTypes.MUTATION, false);
        defaults.put(IPlayerTypes.SPECTATOR, false);
        
        return defaults;
    }
    
}
