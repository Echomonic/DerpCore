package derp.core.dev.managers;

import derp.core.dev.utils.SkullMaker;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class HatManager {

    public static HashMap<String, ItemStack> hats = new HashMap<>();

    public static ItemStack getHat(String text){


        return hats.get(text);
    }


}
