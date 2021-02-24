package derp.core.dev.cosmetics.items;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class NormalItem {

    public static ItemStack stack;

    public static ItemStack getItem(boolean isToggled){


        stack = new ItemStack(Material.INK_SACK, 1, (byte) 10);



        ItemMeta meta = stack.getItemMeta();
        if(isToggled){
            meta.setDisplayName(ChatColor.WHITE + "Players: " + ChatColor.RED + "Hidden " + ChatColor.GRAY  + "(Right Click) " + ChatColor.AQUA + "[BETA]");
            stack = new ItemStack(Material.INK_SACK, 1, DyeColor.SILVER.getData());

        }else{
            meta.setDisplayName(ChatColor.WHITE + "Players: " + ChatColor.GREEN + "Visible " + ChatColor.GRAY  + "(Right Click)" +  ChatColor.AQUA + "[BETA]");
            stack = new ItemStack(Material.INK_SACK, 1, (byte) 10);
        }
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Right-Click to toggle player visibility!"));
        stack.setItemMeta(meta);
        return stack;
    }
}
