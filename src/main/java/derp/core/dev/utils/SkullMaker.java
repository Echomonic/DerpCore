package derp.core.dev.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.xml.namespace.QName;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Collections;
import java.util.UUID;

public class SkullMaker extends ItemStack {

    private String name;

    private String lore;

    private String url;

    public SkullMaker(String name, String lore, String url){
        this.name = name;
        this.lore = lore;
        this.url  = url;

        getSkull();
    }


    public ItemStack getSkull(){
        ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short)3);
        if(url.isEmpty())
            return head;
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode((String.format("{textures:{SKIN:{url:\"%s\"}}}",url)).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try{
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        }catch(NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1){
            e1.printStackTrace();
        }

        headMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&',lore)));
        head.setItemMeta(headMeta);

        return head;
    }


    public String getLore() {
        return lore;
    }

    public String getUrl() {
        return url;
    }
    public String getName() {
        return name;
    }
}
