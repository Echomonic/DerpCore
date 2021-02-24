package derp.core.dev.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Hats {

    LAPIS_SHULKER(new SkullMaker("&9Lapis Shulker", "&7A Lapis Shulker!",
            "http://textures.minecraft.net/texture/7ee66219e7c152788831febf5c5444b8db2ec6c23426668a793c46631f103e5e")
            .getSkull(),0),
    BEDROCK(Inventorys.itemMaker("&8Bedrock", Material.BEDROCK, "&6Um idk how you put this on you're head but ok!"), 1),
    ANGRY_CLUCK(new SkullMaker("&cAngry Boi", "&7This man do be angry","http://textures.minecraft.net/texture/4fb97b17c6395392658f32718aa46befa1c31d3572651c30f7d2bf3b93f6ead9").getSkull(),2),
    CHICKEN_HAT(new SkullMaker("&eChicken Hat", "&7Chicken go quack", "http://textures.minecraft.net/texture/ec79d0d55cc7d06f1c2bcfdc56d809a20efcb857bf79267675e8ec2b8fd89c9a").getSkull(), 3),
    COMMAND_BLOCK(Inventorys.itemMaker("&cCommand Block",Material.COMMAND, "&7A Cool Command Block :D"), 4),
    COMPUTER(new SkullMaker("&8Computer","&7You are now classified as a robot :D ","http://textures.minecraft.net/texture/109cde1afc95a474d222554097ed6d391e7cc7ae1f202fdbfd2d6dbc98309370").getSkull(), 5),
    GOLDEN_DERP(new SkullMaker("&6Golden Derpy","&7Derpy turned to gold!","http://textures.minecraft.net/texture/867293f16f67a015e37d84b5c8ee264b95ea5f0b75760bf280f506b19a2926f7").getSkull(), 6),


    ;

    private final ItemStack hat;
    private final int id;
    Hats(ItemStack hat, int id) {
        this.hat = hat;
        this.id = id;
    }

    public ItemStack getHat() {
        return hat;
    }

    public int getId() {
        return id;
    }
}
