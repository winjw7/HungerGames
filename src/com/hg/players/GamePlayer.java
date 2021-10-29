package com.hg.players;

import java.util.UUID;

import com.hg.stats.GameStatTypes;

import org.bukkit.entity.Player;

/**
 * The player who plays a game
 */
public class GamePlayer {

	private UUID id;
	private String name;
	private IPlayerTypes type;
	private PlayerGameStats stats;
	private PlayerRanks rank;

	/**
	 * Constructor for game player
	 * @param p the player
	 * @param type type of player
	 */
	public GamePlayer(Player p, IPlayerTypes type) {
		this.type = type;
		this.id = p.getUniqueId();
		this.name = p.getName();
		this.stats = new PlayerGameStats(type);

		PlayerRanks[] ranks = PlayerRanks.values();

		for (int i = ranks.length - 1; i >= 0; i--) {
			PlayerRanks r = ranks[i];
			
			if(p.hasPermission(r.getRankPerm()) || (i == 0 && !p.hasPermission(r.getRankPerm()))) {
				this.rank = r;
				break;
			}
		}
	}

	/**
	 * Updates a player type
	 * @param type type
	 */
	public void setPlayerType(IPlayerTypes type) {
		this.type = type;
	}

	/**
	 * Returns the player name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return player's name with color
	 * @return name with color
	 */
	public String getFormattedName() {
		return rank.getNameColor() + name;
	}

	/**
	 * Gets a formated name with prefix
	 * @return name with color and prefix
	 */
	public String getRankFormattedName() {
		return rank.getNamePrefix() + getFormattedName();
	}

	/**
	 * Returns the player id
	 * @return id
	 */
	public UUID getID() {
		return id;
	}

	/**
	 * Returns the player type
	 * @return type
	 */
	public IPlayerTypes getType() {
		return type;
	}

	/**
	 * Whether a player can mutate or not
	 * @return a player can mutate
	 */
	public boolean canMutate() {
		return Boolean.FALSE.equals(stats.getStatValue(GameStatTypes.HAS_MUTATED)) && Boolean.TRUE.equals(stats.getStatValue(GameStatTypes.WAS_ALIVE));
	}

	/**
	 * Whether a player can sponsor or not
	 * @return sponsor
	 */
	public boolean canSponsor() {
		return Boolean.FALSE.equals(stats.getStatValue(GameStatTypes.HAS_SPONSORED)) && isSpectator();
	}

	/**
	 * Gets whether player is alive or not
	 * @return alive
	 */
	public boolean isAlive() {
		return type == IPlayerTypes.TRIBUTE;
	}

	/**
	 * Gets whether player is a mutation
	 * @return mutation
	 */
	public boolean isMutation() {
		return type == IPlayerTypes.MUTATION;
	}

	/**
	 * Gets whether a player is a spectator
	 * @return spectator
	 */
	public boolean isSpectator() {
		return type == IPlayerTypes.SPECTATOR;
	}

	/**
	 * Whether they were a tribute or not
	 * @return if was tribute
	 */
	public boolean wasTribute() {
		return (boolean) stats.getStatValue(GameStatTypes.WAS_ALIVE);
	}

	/**
	 * Get how many mutation passes on win by default
	 * @return mutation passes
	 */
	public int getMinMutationPasses() {
		return rank.getMinMutationPasses();
	}

	public int getRankPower() {
		return rank.getRankPower();
	}
}
