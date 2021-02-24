package derp.core.dev.cosmetics;

import derp.core.dev.utils.CosmeticItem;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

public class EchoesChestplate extends CosmeticItem {

    @Override
    public ItemStack getCosmeticItem(boolean isAdminItem, int price) {

        ArrayList<String> lore = new ArrayList<>();

        ItemStack stack = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD.toString() + "Echo's Chestplate");

        lore.add("  ");

        meta.setColor(Color.GRAY);

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
