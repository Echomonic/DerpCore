package derp.core.dev.utils;

import derp.core.dev.spigot.DerpColor;
import org.bukkit.ChatColor;

public enum GiftingEmotes {

    // ヽ(■_■)ﾉ
    //ヽ(⌐■_■)ﾉ
    //ヽ(¬_¬)ﾉ
    //ヽ(￢ _￢ )ﾉ
    DJ(":dj:", ChatColor.translateAlternateColorCodes('&',
            "&9ヽ&5(&d⌐&c■&6_&e■&b)&3ﾉ&9♫") + reset()),
    ;

    //"ヽ(⌐■_■)ﾉ♫"

    private final String unicode;

    private final String key;


    GiftingEmotes(String key ,String unicode) {
        this.unicode = unicode;
        this.key = key;
    }

    public String getCode() {
        return unicode;
    }

    public String getKey() {
        return key;

    }
    public static ChatColor reset(){


        return DerpColor.RESET.getColor();
    }

}
