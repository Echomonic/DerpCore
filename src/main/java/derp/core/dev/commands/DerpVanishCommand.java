package derp.core.dev.commands;

import derp.core.dev.DerpCore;
import derp.core.dev.spigot.DerpCommand;
import derp.core.dev.utils.Colorize;
import derp.core.dev.utils.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class DerpVanishCommand extends DerpCommand {

    BukkitTask runnable;

    public static ArrayList<Player> vanished = new ArrayList<>();

    public DerpVanishCommand() {
        super("vanish", "vanish.use", false,"&cYou are not a player!");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
            if (sender instanceof Player) {

                Player player = (Player) sender;

                Ranks rank = DerpCore.getFileManager().getRank(player);

                if (rank.getPermissionLevel() >= 6) {
                    if(vanished.contains(player)){
                        for(Player players : Bukkit.getServer().getOnlinePlayers()) {
                            players.showPlayer(player);
                        }
                        vanished.remove(player);
                    }else if(!vanished.contains(player)){

                        for(Player players : Bukkit.getServer().getOnlinePlayers()) {
                            players.hidePlayer(player);

                            if(DerpCore.getFileManager().getRank(players).getPermissionLevel() >=6){
                                players.showPlayer(player);
                            }


                        }



                        vanished.add(player);
                    }

                    new BukkitRunnable(){


                        @Override
                        public void run() {
                            if(vanished.contains(player)){
                                Colorize.actionBar("&fyou are currently&c VANISHED", player);
                            }else{
                                Colorize.actionBar("", player);
                                cancel();
                            }
                        }
                    }.runTaskTimer(DerpCore.instance, 0 ,1);

                }



        }
    }
}