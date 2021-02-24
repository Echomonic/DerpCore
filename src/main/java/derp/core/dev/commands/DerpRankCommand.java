package derp.core.dev.commands;

import derp.core.dev.DerpCore;
import derp.core.dev.spigot.DerpCommand;
import derp.core.dev.utils.Colorize;
import derp.core.dev.utils.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DerpRankCommand extends DerpCommand implements TabCompleter {


    public DerpRankCommand() {
        super("rank", "rank.use", true,"&cYou are not a player!");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        List<String> arguments = new ArrayList<>();

        Player player = (Player) sender;

        if(cmd.getName().equals("rank")) {
            if(DerpCore.getFileManager().getRank(player).getPermissionLevel() >= 7 || player.isOp()){
                if (args.length == 1) {
                    for(Player on : Bukkit.getServer().getOnlinePlayers()){
                        arguments.addAll(Collections.singleton(on.getName()));
                    }
                }

                if (args.length == 2) {

                    for(Ranks ranks : Ranks.values()){
                        arguments.addAll(Collections.singleton(ranks.name().toLowerCase()));
                    }
                }

            }
        }
        return arguments;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            Ranks rank = DerpCore.getFileManager().getRank(player);

            if(rank.getPermissionLevel() >= 7 || player.isOp()){

                Player target = Bukkit.getPlayer(args[0]);

                if(args.length == 2){
                    try {

                        DerpCore.getFileManager().setRank(Ranks.valueOf(args[1].toLowerCase()), target.getUniqueId());

                        target.getScoreboard().getTeam("derped_ranks").setSuffix(Ranks.valueOf(args[1]).getRankColor() + Ranks.valueOf(args[1]).getRankLabel());

                        for(Player players : Bukkit.getOnlinePlayers()){

                            players.getScoreboard().getTeam(target.getName()).setPrefix( DerpCore.getFileManager().getRank(target).getRankLabel() +
                                    DerpCore.getFileManager().getRank(target).getRankColor() + " ");

                        }

                    } catch (IllegalArgumentException e){

                        Colorize.color("&cThis rank was either misspelled : The rank doesn't exist!", player);
                        return ;
                    }


                    Colorize.color("Your rank has been set to " + Ranks.valueOf(args[1]).getRankColor() + Ranks.valueOf(args[1]).getRankLabel(), target);
                    return ;

                }


            }else{
                player.sendMessage(ChatColor.RED + "You don't have permission to preform this command!");
            }

        }else{
            return;
        }
    }
}
