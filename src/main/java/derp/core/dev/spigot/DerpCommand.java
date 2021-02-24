package derp.core.dev.spigot;

import derp.core.dev.DerpCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class DerpCommand implements CommandExecutor {

    private final String commandName;
    private String permission;
    private final boolean console;

    private String consoleMessage;

    public DerpCommand(final String commandName, final String permission, final boolean console, String consoleMessage){
        this.commandName = commandName;
        this.permission = permission;
        this.console = console;
        this.consoleMessage = consoleMessage;
        DerpCore.instance.getCommand(commandName).setExecutor(this);
    }

    public abstract void execute(final CommandSender sender, final String[] args);


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {

        if (!cmd.getLabel().equalsIgnoreCase(commandName))
            return true;

        if (!console && !(sender instanceof Player)) {
            if(getConsoleMessage() == null){
                setConsoleMessage(ChatColor.translateAlternateColorCodes('&',consoleMessage));
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConsoleMessage()));
            return true;
        }
        execute(sender, args);
        return true;
    }



    public String getPermission() {
        return permission;
    }

    public String getConsoleMessage() {
        return consoleMessage;
    }

    public void setConsoleMessage(String consoleMessage) {
        this.consoleMessage = consoleMessage;
    }
}
