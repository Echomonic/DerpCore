package derp.core.dev.cosmetics;

import derp.core.dev.utils.CosmeticItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DerpsHat extends CosmeticItem {
    @Override
    public ItemStack getCosmeticItem(boolean isAdminItem, int price) {

        ArrayList<String> lore = new ArrayList<>();

        ItemStack stack = new ItemStack(Material.GOLD_HELMET);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Derpy's Hat");

        lore.add("  ");

        if(isAdminItem){

            lore.add(ChatColor.RED + ChatColor.BOLD.toString() +"THIS IS AN ADMIN ITEM!");

        }else{
            lore.remove(ChatColor.RED + ChatColor.BOLD.toString() +"THIS IS AN ADMIN ITEM!");
        }

        lore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "∞");

        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);


        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);

        meta.spigot().setUnbreakable(true);

        stack.setItemMeta(meta);

        return stack;
    }
}
