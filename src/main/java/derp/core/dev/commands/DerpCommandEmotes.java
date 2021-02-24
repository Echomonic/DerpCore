package derp.core.dev.commands;

import derp.core.dev.spigot.DerpCommand;
import derp.core.dev.utils.Emotes;
import derp.core.dev.utils.GiftingEmotes;
import derp.core.dev.utils.Ranks;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DerpCommandEmotes extends DerpCommand {

    public DerpCommandEmotes() {
        super("emotes", "emotes.use", false,"&cYou are not a player!");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender instanceof Player){

            Player player = (Player) sender;

            player.sendMessage(ChatColor.GREEN + "Available to " + Ranks.mvp_plus_plus.getRankColor() + Ranks.mvp_plus_plus.getRankLabel());

            for(Emotes emotes : Emotes.values()){

                player.sendMessage(ChatColor.GOLD + emotes.getKey() + ChatColor.WHITE + " - " + emotes.getCode());

            }

            player.sendMessage(ChatColor.GREEN + "Available through Rank Gifting: ");

            for(GiftingEmotes emotes : GiftingEmotes.values()){

                player.sendMessage(ChatColor.GOLD + emotes.getKey() + ChatColor.WHITE + " - " + emotes.getCode());

            }

        }else{

            return ;
        }
    }
}
