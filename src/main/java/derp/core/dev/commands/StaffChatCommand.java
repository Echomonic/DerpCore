package derp.core.dev.commands;

import derp.core.dev.DerpCore;
import derp.core.dev.spigot.DerpCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand extends DerpCommand {

    public StaffChatCommand() {
        super("staffchat", "staffchat.use", true,"&cYou are not a player!");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            if(DerpCore.getFileManager().getRank(player).getPermissionLevel() >= 6){

                String prefix = ChatColor.AQUA + "[STAFF] " + DerpCore.getFileManager().getRank(player).getRankLabel() + DerpCore.getFileManager().getRank(player).getRankColor() + " " + player.getName() + ChatColor.WHITE  + ": ";

                StringBuilder builder  = new StringBuilder();

                for(int i = 0; i < args.length; i++) {
                    builder.append(args[i] + " ");
                }
                String msg = builder.toString();

                for(Player players : Bukkit.getOnlinePlayers()){
                    if(DerpCore.getFileManager().getRank(players).getPermissionLevel() >= 6){
                        players.sendMessage(prefix + msg);
                    }else{
                        return;
                    }
                }

            }else{
                player.sendMessage(ChatColor.RED + "You must be a player to use this command!");
            }

        }else{

            String prefix = ChatColor.AQUA + "[STAFF] " +"§8[§3Console§8]§3 Server" + ChatColor.WHITE + ": ";
            StringBuilder builder  = new StringBuilder();

            for(int i = 0; i < args.length; i++) {
                builder.append(args[i] + " ");
            }
            String msg = builder.toString();
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (DerpCore.getFileManager().getRank(players).getPermissionLevel() >= 6) {
                    players.sendMessage(prefix + msg);
                } else {
                    return;
                }
            }
            return;
        }
    }
}
