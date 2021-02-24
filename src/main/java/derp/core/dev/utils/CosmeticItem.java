package derp.core.dev.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public abstract class CosmeticItem extends ItemStack {

    public abstract ItemStack getCosmeticItem(boolean isAdminItem, int price);

    public boolean isAdminItem(ItemStack stack){
        if(stack.getItemMeta().getLore().contains(ChatColor.RED + ChatColor.BOLD.toString() +"THIS IS AN ADMIN ITEM!")){
            return true;
        }
        return false;
    }

}
