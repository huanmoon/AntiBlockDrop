/*
For some funny reasons, I did something really stupid and some of the files are.. huuuh... hard to read and understand =((
*/

package net.achievevoid.antiblockdrop;

import java.io.File;
import net.achievevoid.antiblockdrop.commands.Claim;
import net.achievevoid.antiblockdrop.listeners.BlockBreakListener;
import net.achievevoid.antiblockdrop.listeners.PlayerGetItemListener;
import net.achievevoid.antiblockdrop.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiBlockDrop extends JavaPlugin {
    public static boolean cliamEnabled;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerGetItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getCommand("claim").setExecutor(new Claim());
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            this.saveResource("config.yml", false);
        }

        this.loadConfig();
        cliamEnabled = this.getConfig().getBoolean("claim-enabled");
        this.getLogger().info("AntiBlockDrop enabled!");
    }

    public void onDisable() {
        this.getLogger().info("AntiBlockDrop disabled!");
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        File configFile = new File(this.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            this.getLogger().warning("Config file doesn't exist!");
        }
        else {
            this.reloadConfig();
        }
    }
}
