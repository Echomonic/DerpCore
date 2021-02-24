package derp.core.dev.utils;

import org.bukkit.ChatColor;

public enum Ranks {

    member("§7Member", ChatColor.GRAY, 1),
    beta("§8[§7Beta§8]", ChatColor.GRAY, 1),
    vip("§8[§aVIP§8]", ChatColor.GREEN, 2),
    vip_plus("§8[§aVIP+§8]", ChatColor.GREEN, 2),
    mvp("§8[§bMVP§8] ", ChatColor.AQUA, 3),
    mvp_plus("§8[§bMVP+§8]", ChatColor.AQUA, 3),
    mvp_plus_plus("§8[§6MVP++§8]", ChatColor.GOLD, 4),


    supere("§8[§1Super§8]", ChatColor.DARK_BLUE, 4),
    hero("§8[§5Hero§8]", ChatColor.DARK_PURPLE, 4),

    youtube("§8[§5YT§8]", ChatColor.DARK_PURPLE, 5),
    youtube_plus("§8[§4YT§f+§8]", ChatColor.DARK_RED, 5),
    twitch("§5Twitch", ChatColor.DARK_PURPLE, 5),

    mod("§8[§3Mod§8]", ChatColor.DARK_GREEN, 6),
    staff("§8[§5Staff§8]", ChatColor.DARK_PURPLE, 6),
    helper("§9Helper", ChatColor.BLUE, 6),
    builder("§1Builder", ChatColor.DARK_BLUE, 6),

    admin("§8[§cAdmin§8]", ChatColor.RED, 7),
    clucker("§8[§9Cluck§8]", ChatColor.BLUE, 7),
    moon("§8[§dMoon§8]", ChatColor.LIGHT_PURPLE, 7),
    derp("§8[§eDerp§8]", ChatColor.YELLOW, 7);

    private final String rankLabel;
    private final ChatColor rankColor;
    private final int permissionLevel;


    Ranks(String rankLabel, ChatColor rankColor, int permLevel) {
        this.rankLabel = rankLabel;
        this.rankColor = rankColor;
        this.permissionLevel = permLevel;
    }

    public ChatColor getRankColor() {
        return rankColor;
    }

    public String getRankLabel() {
        return rankLabel;
    }


    public int getPermissionLevel() {
        return permissionLevel;
    }

}
