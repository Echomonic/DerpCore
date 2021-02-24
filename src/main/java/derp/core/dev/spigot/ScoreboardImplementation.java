package derp.core.dev.spigot;

import derp.core.dev.DerpCore;
import derp.core.dev.utils.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.text.NumberFormat;

public class ScoreboardImplementation {

    public static void refresh(){
        for(Player player : Bukkit.getOnlinePlayers()){
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

            for(Player pl : Bukkit.getOnlinePlayers()){

                String prefix =  DerpCore.getFileManager().getRank(pl).getRankLabel() +
                        DerpCore.getFileManager().getRank(pl).getRankColor() + " ";

                Team team = scoreboard.registerNewTeam(pl.getName());
                team.setPrefix(prefix);
                team.addEntry(pl.getName());
            }

            Objective obj = scoreboard.registerNewObjective("o", "dummy");

            obj.setDisplayName(DerpColor.ORANGE.getColor() + "" + DerpColor.BOLD.getColor()  +"DERPMC");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);


            Score line = obj.getScore("  ");

            line.setScore(8);

            Team ranks = scoreboard.registerNewTeam("derped_ranks");
            ranks.addEntry(DerpColor.WHITE.getColor() + "" + DerpColor.WHITE.getColor());
            ranks.setPrefix(DerpColor.WHITE.getColor() + "Rank: ");
            ranks.setSuffix(DerpCore.getFileManager().getRank(player).getRankColor() + DerpCore.getFileManager().getRank(player).getRankLabel());
            obj.getScore(DerpColor.WHITE.getColor() + "" + DerpColor.WHITE.getColor()).setScore(7);

            Team coins = scoreboard.registerNewTeam("derped_coins");
            coins.addEntry(ChatColor.WHITE + "" + ChatColor.GOLD);
            coins.setPrefix("Coins: ");

            coins.setSuffix(NumberFormat.getInstance().format(DerpCore.getFileManager().getCoin(player).intValue()));

            obj.getScore(ChatColor.WHITE + "" + ChatColor.GOLD).setScore(6);

            Score space2 = obj.getScore("     ");
            space2.setScore(5);

            Score level = obj.getScore("Level:");
            level.setScore(4);

            Score crates = obj.getScore("Crates:");
            crates.setScore(3);

            Score space1 = obj.getScore("    ");
            space1.setScore(2);

            Score wins = obj.getScore("Wins: ");
            wins.setScore(1);



            Score line2 = obj.getScore(" ");
            line2.setScore(0);



            player.setScoreboard(scoreboard);
        }
    }

}
