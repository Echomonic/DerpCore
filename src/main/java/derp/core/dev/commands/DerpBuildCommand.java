package derp.core.dev.commands;

import derp.core.dev.DerpCore;
import derp.core.dev.spigot.DerpCommand;
import derp.core.dev.utils.Ranks;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DerpBuildCommand extends DerpCommand {

    private static List<Player> building = new ArrayList<>();

    public DerpBuildCommand() {
        super("build", "build.derp", false,"&cYou are not a player!");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            Ranks rank = DerpCore.getFileManager().getRank(player);

            if(rank.getPermissionLevel() == 7){

                if(building.contains(player)){

                    player.setGameMode(GameMode.ADVENTURE);
                    building.remove(player);
                }else if(!building.contains(player)){

                    player.setGameMode(GameMode.CREATIVE);
                    building.add(player);
                }
            }else{
                player.sendMessage(ChatColor.RED + "No Permission!");
                return;
            }

        }else{
            sender.sendMessage(ChatColor.RED + "You aren't a player!");
            return;
        }
    }

}
