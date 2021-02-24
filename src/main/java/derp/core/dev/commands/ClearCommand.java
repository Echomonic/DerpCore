package derp.core.dev.commands;

import derp.core.dev.events.Listeners;
import derp.core.dev.spigot.DerpColor;
import derp.core.dev.spigot.DerpCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand extends DerpCommand {

    public ClearCommand() {
        super("clear","clear.use",false,"&cYou are not a player");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        setConsoleMessage("&cYou are not a player!");
        getConsoleMessage();

        if(player.hasPermission(getPermission())){
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            if(!player.getInventory().contains(Listeners.stack)){
                player.getInventory().setItem(4, Listeners.stack);
                Listeners.getToggleHelmet().remove(player);
                Listeners.getToggleChest().remove(player);
                Listeners.getToggleLeggings().remove(player);
                Listeners.getToggleBoots().remove(player);
            }


        }else{
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        }
    }
}
