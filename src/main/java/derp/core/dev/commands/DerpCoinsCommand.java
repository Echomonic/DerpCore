package derp.core.dev.commands;

import derp.core.dev.DerpCore;
import derp.core.dev.spigot.DerpCommand;
import derp.core.dev.utils.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DerpCoinsCommand extends DerpCommand implements TabCompleter {


    NumberFormat format = NumberFormat.getInstance();

    public DerpCoinsCommand() {
        super("coin", "coin.use", false,"&cYou are not a player!");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            Ranks rank = DerpCore.getFileManager().getRank(player);

            if(rank.getPermissionLevel() == 7 || player.isOp()){

                try{

                    if(args.length == 3){

                        Player target = Bukkit.getPlayer(args[1]);
                        int amt = Integer.parseInt(args[2]);

                        switch (args[0].toLowerCase()){



                            case "set":

                                DerpCore.getFileManager().setCoins(amt, target.getUniqueId());

                                target.sendMessage(ChatColor.GOLD + "" +ChatColor.BOLD + "COINS! " + ChatColor.RESET + "Your coins have been set to " + ChatColor.GOLD + format.format(amt));

                                target.getScoreboard().getTeam("derped_coins").setSuffix(ChatColor.GOLD + NumberFormat.getInstance().format(DerpCore.getFileManager().getCoin(target).intValue()));

                                break;


                        }
                    }else if (args.length == 2){

                        switch (args[0].toLowerCase()){

                            case "set":

                                int amt = Integer.parseInt(args[1]);
                                DerpCore.getFileManager().setCoins(amt, player.getUniqueId());


                                player.sendMessage(ChatColor.GOLD + "" +ChatColor.BOLD + "COINS! " + ChatColor.RESET + "Your coins have been set to " + ChatColor.GOLD + format.format(amt));

                                player.getScoreboard().getTeam("derped_coins").setSuffix(ChatColor.GOLD + NumberFormat.getInstance().format(DerpCore.getFileManager().getCoin(player).intValue()));


                                break;

                            case "get":

                                Player target = Bukkit.getPlayer(args[1]);

                                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "ANSWER! " + ChatColor.RESET +
                                        DerpCore.getFileManager().getRank(player).getRankColor() + target.getName() + ChatColor.WHITE + " " + "has " +
                                        ChatColor.GOLD + format.format(DerpCore.getFileManager().getCoin(target)) + ChatColor.WHITE + " coins!");
                                break;

                        }

                    }
                } catch (NumberFormatException exception){
                    player.sendMessage(ChatColor.RED + "(NumberFormatException) There was an error preforming this command!");

                    return ;
                }

            }else{
                player.sendMessage(ChatColor.RED + "No Permission!");
            }


        }else{
            sender.sendMessage(ChatColor.RED  +"You aren't a player!");
            return ;
        }
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String l, String[] args) {
        List<String> arguments = new ArrayList<>();

        Player player = (Player) sender;

        if(sender instanceof Player){
            if(cmd.getName().equals("coin")){
                if(DerpCore.getFileManager().getRank(player).getPermissionLevel() >= 5){
                    if(args.length == 1){
                        arguments.addAll(Arrays.asList("get", "set"));
                    }
                    if(args.length == 2){
                        for(Player players : Bukkit.getServer().getOnlinePlayers()) {
                            arguments.add(players.getName());
                        }
                    }
                }else{
                }
            }
        }
        return arguments;
    }
}
