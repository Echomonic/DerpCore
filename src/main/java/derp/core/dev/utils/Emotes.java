package derp.core.dev.utils;

import derp.core.dev.spigot.DerpColor;
import org.bukkit.ChatColor;

public enum Emotes {

    HEART("<3", ChatColor.RED + "❤" + reset()),
    STAR(":star:", ChatColor.GOLD + "✮" + reset()),
    YES(":yes:", ChatColor.GREEN + "✔" + reset()),
    NO(":no:", ChatColor.RED + "✖" + reset()),
    JAVA(":java:", ChatColor.AQUA + "☕" + reset()),
    ARROW(":arrow:", ChatColor.translateAlternateColorCodes('&', "&e&l➜")),
    SHRUG(":shrug:", ChatColor.YELLOW + "¯\\_(ツ)_/¯" + reset()),
    TABLE_FLIP(":tableflip:", ChatColor.RED + "(╯°□°）╯" + ChatColor.RESET + "︵ ┻━┻" + reset()),
    HELLO("o/", ChatColor.LIGHT_PURPLE + "( ﾟ◡ﾟ)/" + reset()),
    ONE_TWO_THREE(":123:" ,ChatColor.GREEN + "1" + ChatColor.YELLOW + "2" + ChatColor.RED + "3" + reset()),
    TOTEM(":totem:",  ChatColor.AQUA + "◎" + ChatColor.YELLOW + "_"  + ChatColor.AQUA + "◎"),
    TYPING(":typing:", ChatColor.translateAlternateColorCodes('&' ,"&e✎&6...")),
    MATHS(":maths:", ChatColor.translateAlternateColorCodes('&', ChatColor.BOLD + "&a√&e&l(&l&aπ&l+x&e&l)&a&l=&c&lL") + reset()),
    SNAIL(":snail:", ChatColor.translateAlternateColorCodes('&', "&e@&a'&e-&a'") + reset()),
    THINKING(":thinking:", ChatColor.translateAlternateColorCodes('&', "&6(&a0&6.&ao&c?&6)")+ reset()),
    GIMME(":gimme:", ChatColor.translateAlternateColorCodes('&', "&b༼ つ ◕_◕ ༽つ")+ reset()),
    WIZARD(":wizard:", ChatColor.translateAlternateColorCodes('&', "&e(&b'&e-&b'&e)⊃&c━&d☆ﾟ.*･｡ﾟ")+ reset()),
    PVP(":pvp:", ChatColor.translateAlternateColorCodes('&', "&e⚔")+ reset()),
    PEACE(":peace:", ChatColor.translateAlternateColorCodes('&', "&a✌")+ reset()),
    OOF(":oof:", ChatColor.RED + ""  + ChatColor.BOLD + "OOF" + reset()),
    PUFFER(":puffer:", ChatColor.translateAlternateColorCodes('&', ChatColor.BOLD + "&e&l<('O')>") + reset()),
    OWO(":owo:", ChatColor.translateAlternateColorCodes('&', "&dO&5w&dO") + reset()),
    LUNAR(":lunar:", ChatColor.translateAlternateColorCodes('&', "☽") + reset()),

    //Gifting


    ;

    private final String unicode;

    private final String key;


    Emotes(String key ,String unicode) {
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
