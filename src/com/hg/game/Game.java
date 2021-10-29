package com.hg.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.hg.players.GamePlayer;
import com.hg.players.IPlayerTypes;
import com.hg.players.PlayerRanks;
import com.hg.utils.ChatType;
import com.hg.utils.ContentSenderManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Game {

    public final static int MAX_PLAYERS = 24;
    
    private final static int START_COUNTDOWN_PLAYERS = 6;
    private final static int START_DEATHMATCH_PLAYERS = 4;

    private final static int LOBBY_CONTDOWN_TIME = 30;

    private GameStates state;
    private Map map;
    private List<GamePlayer> players;

    public Game() {
        players = new ArrayList<GamePlayer>();
        state = GameStates.WAITING_FOR_PLAYERS;
    }

    private void StartCoundown() {
        this.state = GameStates.LOBBY_COUNTDOWN;
        //ContentSenderManager.BroadcastMessage(, msg);
    }
    
    public void PlayerJoin(Player p) {

        if(getPlayerByID(p.getUniqueId()) == null) {

            GamePlayer gp = new GamePlayer(p, IPlayerTypes.TRIBUTE);

            if(canJoinTributes(gp.getRankPower())) {
                players.add(gp);

                ContentSenderManager.BroadcastMessage(ChatType.INFO, gp.getFormattedName() + ContentSenderManager.defaultTextColor + " has joined the game! (" + getTributeCount() + "/" + MAX_PLAYERS + ")");

                if(state == GameStates.WAITING_FOR_PLAYERS && getTributeCount() >= START_COUNTDOWN_PLAYERS) {
                    StartCoundown();
                }

            } else {
                players.add(new GamePlayer(p, IPlayerTypes.SPECTATOR));
            }
        }
    }

    public void PlayerQuit(Player p) {
        GamePlayer gp = getPlayerByID(p.getUniqueId());

        if(gp == null) 
            return;
            
        if(gp.wasTribute() && inArena()) {

            //kill the player / drop items if needed
            if(gp.isAlive() || gp.isMutation()) {
              
            }

            gp.setPlayerType(IPlayerTypes.SPECTATOR);
        } else {
            players.remove(gp);

            if(!inArena()) {
                ContentSenderManager.BroadcastMessage(ChatType.INFO, gp.getFormattedName() + ContentSenderManager.defaultTextColor + " has left the game! (" + getTributeCount() + "/" + MAX_PLAYERS + ")");
            }
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

    public List<GamePlayer> getPlayersByType(IPlayerTypes type) {
        return players.stream().filter((p) -> p.getType() == type).collect(Collectors.toList());
    }

    public List<GamePlayer> getPlayersByRank(List<GamePlayer> list, int powerRank) {
        return list.stream().filter((p) -> p.getRankPower() == powerRank).collect(Collectors.toList());
    }

    public boolean inArena() {
        return state == GameStates.IN_PROGRESS || state == GameStates.CIRCLE_COUNTDOWN || state == GameStates.DEATHMATCH_COUNTDOWN || state == GameStates.COMPLETED;
    }

    /**
     * Checks to see if player can join the game as a tribute
     * @param rankPower the players rank power
     * @return can join
     */
    public boolean canJoinTributes(int rankPower) {
        
        //The game can only be joined during lobby
        if(state != GameStates.WAITING_FOR_PLAYERS && state != GameStates.LOBBY_COUNTDOWN) 
            return false; 
        
        //if there is less than 24 ppl anyone can join
        else if(getTributeCount() < MAX_PLAYERS) 
            return true;

        //if game is full and default player then can't join
        else if(rankPower == 0)
            return false;

        else {
            int lowestRankValue = PlayerRanks.DEFAULT.getRankPower();
            int highestRankValue = PlayerRanks.OWNER.getRankPower();

            List<GamePlayer> tributes = getPlayersByType(IPlayerTypes.TRIBUTE);

            for(int i = lowestRankValue; i < highestRankValue; i++) {   
                
                if(i >= rankPower)
                    return false;

                List<GamePlayer> rankPlayers = getPlayersByRank(tributes, i);

                if(rankPlayers.size() > 0) {
                    GamePlayer toKick = rankPlayers.get(rankPlayers.size() - 1);
                    Player p = Bukkit.getPlayer(toKick.getID());
                    
                    players.remove(toKick);
                    p.kickPlayer("Sorry you were kicked out for a premium player, visit our website to purchase a rank!");

                    return true;
                }
            }

            return false;
        }
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
