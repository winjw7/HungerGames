package com.hg.stats;

import java.util.Map;

import com.hg.players.IPlayerTypes;

public abstract class GameStat<T> {

    private T value;
    private GameStatTypes statType;
    
    /**
     * Constructor for game stat
     * @param statType the stat type
     * @param type the player type
     * @param defaults the defaults
     * @throws Exception
     */
    public GameStat(GameStatTypes statType, IPlayerTypes type, Map<IPlayerTypes, T> defaultValues) throws Exception {

        if(defaultValues.size() != IPlayerTypes.values().length) {
            throw new Exception("Don't have defaults for all values");
        }

        this.statType = statType;
        this.value = defaultValues.get(type);
    }

     /**
     * Constructor for game stat
     * @param statType the stat type
     * @param value the value
     */
    public GameStat(GameStatTypes statType, T value) {
        this.value = value;
        this.statType = statType;
    }

    /**
     * Gets the stat value
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets a value
     * @param T new value
     */
    public void setValue(T value) {
        this.value = value;
    }

    public GameStatTypes getStatType() {
        return statType;
    }
}

