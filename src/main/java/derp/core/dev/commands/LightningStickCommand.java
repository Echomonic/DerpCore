package derp.core.dev.commands;

import derp.core.dev.spigot.DerpCommand;
import derp.core.dev.utils.Colorize;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static derp.core.dev.utils.Inventorys.itemMaker;

public class LightningStickCommand extends DerpCommand {

    public LightningStickCommand() {
        super("lightningstick", "lightningstick.abuse", false
                , "&cYour not a player so you cannot preform this command!");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if(player.hasPermission(getPermission())){

            player.getInventory().addItem(itemMaker("&f&lLightning Stick", Material.STICK,"&7Strike lightning where ever you click!"));

        }else{
            Colorize.color("&cYou must be admin or higher to use this command!",player);
        }


    }
}
