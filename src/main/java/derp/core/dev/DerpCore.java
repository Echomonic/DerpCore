package derp.core.dev;

import derp.core.dev.commands.*;
import derp.core.dev.events.Listeners;
import derp.core.dev.managers.FileManager;
import derp.core.dev.utils.Colorize;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class DerpCore extends JavaPlugin {

    private static FileManager fileManager;

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static DerpCore instance;

    public static HashMap<UUID, Player> vanished;

    @Override
    public void onEnable() {

        vanished = new HashMap<>();

        instance = this;

        fileManager = new FileManager(this);


        getCommands();
        for(Player player : Bukkit.getOnlinePlayers()) {
                Listeners.getInv().remove(player.getUniqueId());

                if (player.getInventory().getArmorContents() != null) {
                    Listeners.getToggleHelmet().remove(player.getUniqueId());
                    Listeners.getToggleChest().remove(player.getUniqueId());
                    Listeners.getToggleLeggings().remove(player.getUniqueId());
                    Listeners.getToggleBoots().remove(player.getUniqueId());

                } else {
                    Listeners.getToggleHelmet().add(player.getUniqueId());
                    Listeners.getToggleChest().add(player.getUniqueId());
                    Listeners.getToggleLeggings().add(player.getUniqueId());
                    Listeners.getToggleBoots().add(player.getUniqueId());
                }
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if(getFileManager().containsPlayer(player)) {
                        Colorize.actionBar("you are currently&c VANISHED", player);
                    }else{
                        cancel();
                    }
                }
            }.runTaskTimer(this, 0, 1);
        }
        Bukkit.getPluginManager().registerEvents(new Listeners(),this);

    }

    @Override
    public void onDisable() {

    }

    private void getCommands(){

        new DerpBuildCommand();
        new DerpCoinsCommand();
        new DerpVanishCommand();
        new StaffChatCommand();
        new DerpCommandEmotes();
        new DerpRankCommand();
        new ClearCommand();
        new LightningStickCommand();
    }
}
