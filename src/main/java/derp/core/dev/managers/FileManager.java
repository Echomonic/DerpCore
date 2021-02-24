package derp.core.dev.managers;

import derp.core.dev.DerpCore;
import derp.core.dev.utils.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class FileManager {

    private final File ranks;

    private final FileConfiguration rankConfig;

    private final File coins;

    private final FileConfiguration coinConfig;

    private final File level;

    private final FileConfiguration levelConfig;


    private final File vanish;

    private final FileConfiguration vanishConfig;

    private Ranks rank;

    private int coin;

    public FileManager(DerpCore core) {

        ranks = new File(core.getDataFolder(), "rankData.yml");

        coins = new File(core.getDataFolder(), "coinData.yml");

        level = new File(core.getDataFolder(), "levelData.yml");

        vanish = new File(core.getDataFolder(), "vanished.yml");

        if(!ranks.exists() || !coins.exists() || !level.exists() || !vanish.exists()){
            try {
                ranks.createNewFile();
                coins.createNewFile();
                level.createNewFile();
                vanish.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!core.getDataFolder().exists()){
            core.getDataFolder().mkdir();
        }

        this.rankConfig = YamlConfiguration.loadConfiguration(ranks);
        this.coinConfig = YamlConfiguration.loadConfiguration(coins);
        this.levelConfig = YamlConfiguration.loadConfiguration(level);
        this.vanishConfig = YamlConfiguration.loadConfiguration(vanish);

    }

    public void save(){

        try {
            rankConfig.save(ranks);
            coinConfig.save(coins);
            levelConfig.save(level);
            vanishConfig.save(vanish);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void vanishPlayer(Player player){

        for(Player players : Bukkit.getOnlinePlayers()){
            players.hidePlayer(player);
        }

        vanishConfig.set(player.getUniqueId().toString(),player.getUniqueId().toString());
        save();
    }
    public void unVanishPlayer(Player player){

        for(Player players : Bukkit.getOnlinePlayers()){
            players.showPlayer(player);
        }

        vanishConfig.set(player.getUniqueId().toString(),null);
        save();
    }


    public boolean containsPlayer(Player player){

      if(getVanishConfig().contains(player.getUniqueId().toString())){
            return true;
        }

        return false;
    }

    public FileConfiguration getVanishConfig() {
        return vanishConfig;
    }

    public Integer getCoin(Player player) {
        return coinConfig.getInt(player.getUniqueId().toString());
    }

    public void setCoins(int amt, Player player) {

        coinConfig.set(player.getUniqueId().toString(),amt);
        save();
    }

    public void setCoins(int amt, UUID uuid) {

        coinConfig.set(uuid.toString(), amt);
        save();
    }

    public Boolean hasCoin(Player player){

        if(coinConfig.getInt(player.getUniqueId().toString()) > 0){
            return true;
        }

        return null;
    }

    public Integer getLevel(Player player) {
        return coinConfig.getInt(player.getUniqueId().toString());
    }

    public void setLevel(int amt, Player player) {

        levelConfig.set(player.getUniqueId().toString(),amt);
        save();
    }

    public void setLevel(int amt, UUID uuid) {

        levelConfig.set(uuid.toString(), amt);
        save();
    }

    public Boolean hasLevel(Player player){

        if(levelConfig.getInt(player.getUniqueId().toString()) > 0){
            return true;
        }

        return null;
    }

    public Ranks getRank(Player player) {

        return Ranks.valueOf(rankConfig.getString(player.getUniqueId().toString()));
    }

    public void setRank(Ranks rank, Player player) {

        rankConfig.set(player.getUniqueId().toString(), rank.name());
        save();
    }

    public void setRank(Ranks rank, UUID uuid) {

        rankConfig.set(uuid.toString(), rank.name());
        save();
    }

    public boolean hasRank(Player player){

        if(rankConfig.getString(player.getUniqueId().toString()) != null){
            return true;
        }
        return false;
    }
}
