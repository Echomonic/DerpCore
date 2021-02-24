package derp.core.dev.managers;

import com.avaje.ebean.AdminAutofetch;
import derp.core.dev.cosmetics.AdminBoots;
import derp.core.dev.cosmetics.CluckerPants;
import derp.core.dev.cosmetics.DerpsHat;
import derp.core.dev.cosmetics.EchoesChestplate;
import derp.core.dev.utils.CosmeticItem;
import derp.core.dev.utils.Inventorys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

import static derp.core.dev.utils.Inventorys.itemMaker;

public class CosmeticManager {

    private static HashMap<String, CosmeticItem> cosmeticItem = new HashMap<>();
    private static HashMap<String, CosmeticItem> suits = new HashMap<>();
    private static HashMap<ItemStack, ItemStack> suitItems = new HashMap<>();

    private static DerpsHat hat = new DerpsHat();
    private static CluckerPants clucker = new CluckerPants();

    public static ItemStack getItem(String s, boolean isAdminItem, int i){

        cosmeticItem.put("DERPS_HAT", hat);
        cosmeticItem.put("CLUCKERS_PANTS", clucker);

        return cosmeticItem.get(s).getCosmeticItem(isAdminItem, i);
    }

    public static Collection<CosmeticItem> getAllItems(){


        return cosmeticItem.values();
    }

    private static HashMap<ItemStack,CosmeticItem> items = new HashMap<>();

    public static CosmeticItem getSpecificItem(ItemStack stack){
        items.put(hat.getCosmeticItem(true, 0), hat);
        items.put(clucker.getCosmeticItem(true, 0), clucker);
        return items.get(stack);
    }

    public static ItemStack getSuit(String s, boolean isAdminItem, int i){

        DerpsHat hat = new DerpsHat();
        EchoesChestplate chest = new EchoesChestplate();
        CluckerPants legs = new CluckerPants();
        AdminBoots boots = new AdminBoots();

        suits.put("DERPS_HAT", hat);
        suits.put("ECHOS_CHESTPLATE",chest);
        suits.put("CLUCKERS_PANTS", legs);
        suits.put("ADMIN_BOOTS", boots);



        return suits.get(s).getCosmeticItem(isAdminItem, i);
    }

    public static Collection<CosmeticItem> getSuits() {

        return suits.values();
    }
    public ItemStack getSuitItem(ItemStack stack){
        suitItems.put(itemMaker(ChatColor.RED + "Admin Suit", Material.COMMAND, "&cThis is an admin suit!"),
                itemMaker(ChatColor.RED + "Admin Suit", Material.COMMAND, "&cThis is an admin suit!"));
        suitItems.put(itemMaker(ChatColor.RED + "Admin Suit", Material.COMMAND, "&cThis is an admin suit!"),
                itemMaker(ChatColor.BLUE + "Lapis Armor", Material.LAPIS_ORE, "&7Woah you are made of lapis!"));

        return suitItems.get(stack);
    }
}
