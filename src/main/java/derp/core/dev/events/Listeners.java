package derp.core.dev.events;

import derp.core.dev.DerpCore;
import derp.core.dev.commands.DerpVanishCommand;
import derp.core.dev.managers.CosmeticManager;
import derp.core.dev.spigot.ScoreboardImplementation;
import derp.core.dev.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static derp.core.dev.utils.Inventorys.itemMaker;

public class Listeners implements Listener {

    public static ItemStack stack = Inventorys.itemMaker(ChatColor.YELLOW + "Cosmetics" , Material.CHEST, "&7This allows you to show all your cool cosmetics to your friends!");

    private static final HashMap<UUID, Inventory> inv = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(null);


        if (!player.hasPlayedBefore() ||
                !DerpCore.getFileManager().hasRank(player)) {

            DerpCore.getFileManager().setRank(Ranks.member, player);
        } else {

            DerpCore.getFileManager().getRank(player);

        }
        ScoreboardImplementation.refresh();


        if(DerpCore.getFileManager().getRank(player).getPermissionLevel() >= 6) {
            String prefix = ChatColor.AQUA + "[STAFF] " + DerpCore.getFileManager().getRank(player).getRankLabel() + DerpCore.getFileManager().getRank(player).getRankColor() + " " + player.getName();
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (DerpCore.getFileManager().getRank(players).getPermissionLevel() >= 6) {
                    if(players.getUniqueId().toString().equals(player.getUniqueId().toString())) continue;
                    players.sendMessage(prefix + ChatColor.YELLOW + " joined!");
                }



            }


        }else{
            return;
        }

        new BukkitRunnable(){


            @Override
            public void run() {

                if(DerpVanishCommand.vanished.contains(player)){
                    Colorize.actionBar("&fyou are currently&b&l VANISHED", player);
                }else{
                    Colorize.actionBar("", player);
                    cancel();
                }

            }
        }.runTaskTimer(DerpCore.instance, 0 ,1);

        player.getInventory().setItem(4, stack);
        for (int i = 0; i < DerpVanishCommand.vanished.size(); i++) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (DerpVanishCommand.vanished.get(i).getUniqueId().toString().equals(players.getUniqueId().toString()))
                    continue;

                DerpVanishCommand.vanished.add(player);


                players.hidePlayer(DerpVanishCommand.vanished.get(i));
                event.setJoinMessage(null);
            }
        }


    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){

        Player player = event.getPlayer();
        Ranks ranks = DerpCore.getFileManager().getRank(player);

        event.setCancelled(true);


        for(Player rank : event.getRecipients()){



            String prefix = ranks.getRankColor() + ranks.getRankLabel() + ranks.getRankColor() + " ";


            String prefixs = Ranks.member.getRankColor() + Ranks.member.getRankLabel() + Ranks.member.getRankColor() + " ";


            if(ranks == Ranks.member){
                rank.sendMessage(prefixs + player.getName() + ": " + event.getMessage());
            }else{

                if(ranks.getPermissionLevel() >= 4){
                    for(Emotes emotes : Emotes.values()) {
                        if (event.getMessage().contains(emotes.getKey())) {
                            String uni = event.getMessage().replace(emotes.getKey(), emotes.getCode());
                            event.setMessage(uni);
                        }
                    }
                }

                if(ranks.getPermissionLevel() > 6){
                    for(GiftingEmotes emotes : GiftingEmotes.values()) {
                        if (event.getMessage().contains(emotes.getKey())) {
                            String uni = event.getMessage().replace(emotes.getKey(), emotes.getCode());
                            event.setMessage(uni);
                        }
                    }
                }
                rank.sendMessage(prefix + player.getName() + ChatColor.WHITE + ": " + event.getMessage());
            }
        }
    }






    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();

        if(player.getInventory().getItemInHand().isSimilar(stack)){

            if(!inv.containsKey(player.getUniqueId())) {
                player.openInventory(Inventorys.mainInventory(player));
                inv.put(player.getUniqueId(), Inventorys.mainInventory(player));
            }else{
                player.openInventory(Inventorys.mainInventory(player));
            }
        } else if(player.getInventory().getItemInHand().isSimilar(itemMaker("&f&lLightning Stick", Material.STICK,"&7Strike lightning where ever you click!"))){
            player.getTargetBlock((Set<Material>) null,50).getWorld().strikeLightningEffect(player.getTargetBlock((Set<Material>) null,50).getLocation());
        }else{
            return;
        }
        event.setCancelled(true);
    }

    public static ArrayList<Player> pl = new ArrayList<>();



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        Ranks rank = DerpCore.getFileManager().getRank(player);

        if(event.getInventory().getTitle().contains("Cosmetics")){

            switch (event.getSlot()){

                case 10:
                    player.openInventory(Inventorys.hatsInventory(player));
                    inv.put(player .getUniqueId(), Inventorys.hatsInventory(player));
                    break;

                case 12:
                    player.openInventory(Inventorys.AcutalHatInventory(player));
                    inv.put(player .getUniqueId(), Inventorys.AcutalHatInventory(player));
                    break;
            }

        }else if(event.getInventory().getTitle().contains("Suits")) {

            switch (event.getSlot()){

                case 10:
                    if(rank.getPermissionLevel() >= 7) {
                        player.openInventory(Inventorys.suitInventory("Admin-Suit",CosmeticManager.getSuit("DERPS_HAT", true, 0),
                                CosmeticManager.getSuit("ECHOS_CHESTPLATE", true, 0),
                                CosmeticManager.getSuit("CLUCKERS_PANTS", true, 0),
                                CosmeticManager.getSuit("ADMIN_BOOTS", true, 0)));
                    }else{
                        player.sendMessage(ChatColor.RED + "This is for admins only!");
                    }
                    break;


                case 11:
                    player.openInventory(Inventorys.suitInventory("Lapis-Armor",
                            Inventorys.itemMaker("&9Lapis Helmet", Material.LAPIS_ORE,""),
                            Inventorys.leatherArmorMaker("&9Lapis Chestplate",Material.LEATHER_CHESTPLATE, Color.BLUE,""),
                            Inventorys.leatherArmorMaker("&9Lapis Leggings",Material.LEATHER_LEGGINGS, Color.BLUE,""),
                            Inventorys.leatherArmorMaker("&9Lapis Boots",Material.LEATHER_BOOTS, Color.BLUE,"")));

                    break;
            }
        }else if(event.getInventory().getTitle().contains("Admin-Suit")) {



            check(event, player, event.getCurrentItem(),
                    event.getCurrentItem(),
                    event.getCurrentItem(),
                    event.getCurrentItem(), 4, 13, 22, 31);



            if(event.getSlot() == 39){
                toggleHelmet.remove(player.getUniqueId());
                toggleChest.remove(player.getUniqueId());
                toggleLeggings.remove(player.getUniqueId());
                toggleBoots.remove(player.getUniqueId());
                player.getInventory().setBoots(null);
                player.getInventory().setLeggings(null);
                player.getInventory().setChestplate(null);
                player.getInventory().setHelmet(null);

            }



            if(event.getSlot() == 41){
                toggleHelmet.add(player.getUniqueId());
                toggleChest.add(player.getUniqueId());
                toggleLeggings.add(player.getUniqueId());
                toggleBoots.add(player.getUniqueId());
                player.getInventory().setBoots(CosmeticManager.getSuit("ADMIN_BOOTS", true,0));
                player.getInventory().setLeggings(CosmeticManager.getSuit("CLUCKERS_PANTS", true, 0));
                player.getInventory().setChestplate(CosmeticManager.getSuit("ECHOS_CHESTPLATE", true, 0));
                player.getInventory().setHelmet(CosmeticManager.getSuit("DERPS_HAT", true, 0));

            }

            player.updateInventory();
        } else if(event.getInventory().getTitle().contains("Hats")) {

            if(event.getCurrentItem() == null) return;

            check(event, player, event.getCurrentItem(),
                    player.getInventory().getChestplate(), player.getInventory().getLeggings(), player.getInventory().getBoots(), event.getSlot(), 0, 0, 0);
        }else if(event.getInventory().getTitle().contains("Lapis-Armor")) {


            check(event, player, event.getCurrentItem(),
                    event.getCurrentItem(),
                    event.getCurrentItem(),
                    event.getCurrentItem(), 4, 13, 22, 31);


            if (event.getSlot() == 39) {
                toggleHelmet.remove(player.getUniqueId());
                toggleChest.remove(player.getUniqueId());
                toggleLeggings.remove(player.getUniqueId());
                toggleBoots.remove(player.getUniqueId());
                player.getInventory().setBoots(null);
                player.getInventory().setLeggings(null);
                player.getInventory().setChestplate(null);
                player.getInventory().setHelmet(null);

            }


            if (event.getSlot() == 41) {
                toggleHelmet.add(player.getUniqueId());
                toggleChest.add(player.getUniqueId());
                toggleLeggings.add(player.getUniqueId());
                toggleBoots.add(player.getUniqueId());



                player.getInventory().setBoots(Inventorys.leatherArmorMaker("&9Lapis Boots",Material.LEATHER_BOOTS, Color.BLUE,""));
                player.getInventory().setLeggings(Inventorys.leatherArmorMaker("&9Lapis Leggings",Material.LEATHER_LEGGINGS, Color.BLUE,""));
                player.getInventory().setChestplate(Inventorys.leatherArmorMaker("&9Lapis Chestplate",Material.LEATHER_CHESTPLATE, Color.BLUE,""));
                player.getInventory().setHelmet(Inventorys.itemMaker("&9Lapis Helmet", Material.LAPIS_ORE,""));

            }

            player.updateInventory();
        }else{
            return;
        }
        event.setCancelled(true);
    }



    private static List<UUID> toggleHelmet = new ArrayList<>();
    private static List<UUID> toggleChest = new ArrayList<>();
    private static List<UUID> toggleLeggings = new ArrayList<>();
    private static List<UUID> toggleBoots = new ArrayList<>();

    public void check(InventoryClickEvent event, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, int i, int j, int k, int g) {

        if(event.getCurrentItem() == null) return;

        if (event.getSlot() == i) {
            if (this.toggleHelmet.contains(player.getUniqueId())) {
                player.getInventory().setHelmet(null);
                event.getCurrentItem().getItemMeta().removeEnchant(Enchantment.DURABILITY);
                this.toggleHelmet.remove(player.getUniqueId());
            } else {
                player.getInventory().setHelmet(helmet);
                this.toggleHelmet.add(player.getUniqueId());
            }
        } else if (event.getSlot() == j) {
            if (this.toggleChest.contains(player.getUniqueId())) {
                player.getInventory().setChestplate((ItemStack)null);
                this.toggleChest.remove(player.getUniqueId());
            } else {
                player.getInventory().setChestplate(chestplate);
                this.toggleChest.add(player.getUniqueId());
            }
        } else if (event.getSlot() == k) {
            if (this.toggleLeggings.contains(player.getUniqueId())) {
                player.getInventory().setLeggings((ItemStack)null);
                this.toggleLeggings.remove(player.getUniqueId());
            } else {
                player.getInventory().setLeggings(leggings);
                this.toggleLeggings.add(player.getUniqueId());
            }
        } else {
            if (event.getSlot() != g) {
                return;
            }

            if (this.toggleBoots.contains(player.getUniqueId())) {
                player.getInventory().setBoots((ItemStack)null);
                this.toggleBoots.remove(player.getUniqueId());
            } else {
                player.getInventory().setBoots(boots);
                this.toggleBoots.add(player.getUniqueId());
            }
        }

    }

    public static HashMap<UUID, Inventory> getInv(){

        return inv;
    }

    public static List<UUID> getToggleHelmet() {
        return toggleHelmet;
    }

    public static List<UUID> getToggleChest() {
        return toggleChest;
    }

    public static List<UUID> getToggleLeggings() {
        return toggleLeggings;
    }

    public static List<UUID> getToggleBoots() {
        return toggleBoots;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();

        event.setQuitMessage(null);


        if(DerpCore.getFileManager().getRank(player).getPermissionLevel() >= 6 && player.getWorld().getName().equals("Derp Server")) {

            String prefix = ChatColor.AQUA + "[STAFF] " + DerpCore.getFileManager().getRank(player).getRankLabel() + DerpCore.getFileManager().getRank(player).getRankColor() + " " + player.getName();
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (DerpCore.getFileManager().getRank(players).getPermissionLevel() >= 6) {
                    players.sendMessage(prefix + ChatColor.YELLOW + " left!");
                }


            }

        }else{
            return;
        }

    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){

        Player player = event.getPlayer();

        if(DerpCore.getFileManager().getRank(player).getPermissionLevel() < 6 ) {



        }else{
            return;
        }
        event.setCancelled(true);
    }



}
