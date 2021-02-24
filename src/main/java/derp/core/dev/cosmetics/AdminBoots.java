package derp.core.dev.cosmetics;

import derp.core.dev.utils.CosmeticItem;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

public class AdminBoots extends CosmeticItem {

    @Override
    public ItemStack getCosmeticItem(boolean isAdminItem, int price) {

        ArrayList<String> lore = new ArrayList<>();

        ItemStack stack = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();

        meta.setColor(Color.RED);

        meta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Admins Boots");

        lore.add("  ");

        if(isAdminItem){

            lore.add(ChatColor.RED + ChatColor.BOLD.toString() +"THIS IS AN ADMIN ITEM!");

        }else{
            lore.remove(ChatColor.RED + ChatColor.BOLD.toString() +"THIS IS AN ADMIN ITEM!");
        }

        lore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "∞");

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);

        meta.spigot().setUnbreakable(true);

        stack.setItemMeta(meta);

        return stack;
    }
}
