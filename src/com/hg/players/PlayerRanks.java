package com.hg.players;

import com.hg.utils.ContentSenderManager;

public enum PlayerRanks {
    DEFAULT("", "&r&7", "", 0, 0),
    IRON("rank.iron", "&r&f", "&f&lIRON ", 1, 1),
    GOLD("rank.gold", "&r&6", "&6&lGOLD ", 2, 2),
    DIAMOND("rank.diamond", "&r&b", "&b&lDIAMOND ", 3, 3),
    YOUTUBER("rank.youtuber", "&r&c", "&c&lYOUTUBER ", 3, 4),
    MOD("rank.mod", "&r&9", "&9&lMOD ", 3, 5),
    ADMIN("rank.admin", "&r&d", "&d&lADMIN ", 3, 6),
    OWNER("rank.owner", "&r&4", "&4&lOWNER ", 3, 7);

    private String namePrefix;
    private String nameColor;
    private String rankPerm;
    private int minMutationPassesOnWin;
    private int rankPower;

    private PlayerRanks(String rankPerm, String nameColor, String namePrefix, int minMutationPassesOnWin, int rankPower) {
        this.rankPerm = rankPerm;
        this.nameColor = ContentSenderManager.ColorMessage(nameColor);
        this.namePrefix = ContentSenderManager.ColorMessage(namePrefix);
        this.minMutationPassesOnWin = minMutationPassesOnWin;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getNameColor() {
        return nameColor;
    }

    public int getMinMutationPasses() {
        return minMutationPassesOnWin;
    }

    public String getRankPerm() {
        return rankPerm;
    }

    public int getRankPower() {
        return rankPower;
    }
}
