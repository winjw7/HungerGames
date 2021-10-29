package com.hg.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hg.players.GamePlayer;
import com.hg.players.IPlayerTypes;

import org.bukkit.entity.Player;

public class Game {

    public final static int MAX_PLAYERS = 24;
    public final static int START_DEATHMATCH_PLAYERS = 4;

    private GameStates state;
    private Map map;
    private List<GamePlayer> players;

    public Game() {
        players = new ArrayList<GamePlayer>();
    }
    
    public void PlayerJoin(Player p) {
        if(getPlayerByID(p.getUniqueId()) == null) {
            if(canJoinTributes()) {
                players.add(new GamePlayer(p, IPlayerTypes.TRIBUTE));
            } else {
                players.add(new GamePlayer(p, IPlayerTypes.SPECTATOR));
            }
        }
    }

    public void PlayerQuit(Player p) {
        GamePlayer gp = getPlayerByID(p.getUniqueId());

        if(gp.wasTribute()) {

            //Drop player items and death
            if(gp.isAlive()) {
              
            }

            gp.setPlayerType(IPlayerTypes.SPECTATOR);
        } else {
            players.remove(gp);
        }
    }

    /**
     * Gets how many tributes there are
     * @return tribute count
     */
    public int getTributeCount() {
        int i = 0;

        for(GamePlayer player : players) {
            if(player.isAlive()) {
                i++;
            }
        }

        return i;
    }

    public boolean canJoinTributes() {
        return state == GameStates.WAITING_FOR_PLAYERS && getTributeCount() < MAX_PLAYERS;
    }

    /**
     * Gets whether a player can drop an item
     * @param gp the game player to check
     * @return if can drop
     */
    public boolean canDropItem(GamePlayer gp) {
        return (state == GameStates.IN_PROGRESS || state == GameStates.DEATHMATCH_COUNTDOWN) && gp.isAlive();
    }

    /**
     * Gets whether the game is in maintence mode
     * @return if maintence mode
     */
    public boolean isMaintenance() {
        return state == GameStates.MAINTENANCE;
    }

    /**
     * Gets a player in the game by id
     * @param id the id of the player
     * @return the player
     */
    public GamePlayer getPlayerByID(UUID id) {
        if(players.size() == 0)
            return null;

        for(GamePlayer player : players) {
            if(player.getID() == id) {
                return player;
            }
        }

        return null;
    }
}
