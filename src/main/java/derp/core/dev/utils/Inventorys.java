package derp.core.dev.utils;

import derp.core.dev.managers.CosmeticManager;
import derp.core.dev.managers.HatManager;
import derp.core.dev.managers.SkullManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Inventorys {

    public static Inventory mainInventory(Player player){

        Inventory inventory = Bukkit.createInventory(null,6*9, ChatColor.GRAY + "Cosmetics");

        inventory.setItem(10, leatherArmorMaker(ChatColor.AQUA + "Suits",Material.LEATHER_CHESTPLATE,Color.BLUE, "&7Cool Suits!"));
        inventory.setItem(12, new SkullMaker("&bHats", "Get cool hats!", "http://textures.minecraft.net/texture/ea1ceac9721771235a337b28e1f8b11faa43128c9567afa1f5d110ddbef56bf5").getSkull());
        inventory.setItem(14, itemMaker("&5Particle Effects &c[NOT CODED]",Material.ENCHANTMENT_TABLE, "&7This is unfinished!"));

        return inventory;
    }






    public static Inventory hatsInventory(Player player){

        Inventory inventory = Bukkit.createInventory(null,9*3, ChatColor.GRAY + "Suits");

        inventory.setItem(10, itemMaker(ChatColor.RED + "Admin Suit", Material.COMMAND, "&cThis is an admin suit!"));
        inventory.setItem(11, itemMaker(ChatColor.BLUE + "Lapis Armor", Material.LAPIS_ORE, "&7Woah you are made of lapis!"));


        return inventory;
    }

    public static Inventory AcutalHatInventory(Player player){

        Inventory inventory = Bukkit.createInventory(null,9*3, ChatColor.GRAY + "Hats");

        for(int g = 0; g < inventory.getSize(); g++) {

            int i = 10;
            for (Hats hats : Hats.values()) {
                inventory.setItem(i, hats.getHat());

                i++;
            }
            if(inventory.getItem(g) == null){
                inventory.setItem(g, glassMaker(DyeColor.GRAY,true));
            }
        }
        return inventory;
    }




    public static Inventory suitInventory(String thing,ItemStack helmet, ItemStack chestplate,ItemStack leggings, ItemStack boots){

        Inventory inventory = Bukkit.createInventory(null,9*5, ChatColor.GRAY + "View " + thing);

        inventory.setItem(4, helmet);
        inventory.setItem(13, chestplate);
        inventory.setItem(22, leggings);
        inventory.setItem(31, boots);

        inventory.setItem(39, itemMaker("&cTake off The suit!", Material.BARRIER, ""));
        inventory.setItem(41, itemMaker("&aEquip The full suit!", Material.LEATHER, ""));

        return inventory;
    }





    public static ItemStack itemMaker(String name, Material material, String lore){

        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        meta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', lore)));

        stack.setItemMeta(meta);

        return stack;
    }
    public static ItemStack glassMaker(DyeColor color,boolean isPane){

        ItemStack stack;
        if(!isPane){
            stack = new ItemStack(Material.STAINED_GLASS, 1,color.getData());
        }else{
            stack = new ItemStack(Material.STAINED_GLASS_PANE, 1,color.getData());
        }
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("  ");

        stack.setItemMeta(meta);
        return stack;
    }
    public static ItemStack leatherArmorMaker(String name, Material material, Color color, String lore){

        ItemStack stack = new ItemStack(material);
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        meta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', lore)));

        meta.setColor(color);

        stack.setItemMeta(meta);

        return stack;
    }
}
