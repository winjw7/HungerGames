package com.hg.stats;

import java.util.HashMap;
import java.util.Map;

import com.hg.players.IPlayerTypes;

public class HasSponsored extends GameStat<Boolean> {

    public HasSponsored(IPlayerTypes type) throws Exception {
        super(GameStatTypes.HAS_SPONSORED, type, defaultValues());
    }

    public static Map<IPlayerTypes, Boolean> defaultValues() {

        Map<IPlayerTypes, Boolean> defaults = new HashMap<IPlayerTypes, Boolean>();

        defaults.put(IPlayerTypes.TRIBUTE, false);
        defaults.put(IPlayerTypes.MUTATION, true);
        defaults.put(IPlayerTypes.SPECTATOR, true);
        
        return defaults;
    }
}
