package com.hg.game;

import java.util.ArrayList;
import java.util.List;

import com.hg.players.GamePlayer;

public class Game {

    public final static int MAX_PLAYERS = 24;
    public final static int START_DEATHMATCH_PLAYERS = 4;

    private GameStates state;
    private Map map;
    private List<GamePlayer> players;

    public Game() {
        players = new ArrayList<GamePlayer>();
    }
    
}
