package BetterChatColor;

import BetterChatColor.Commands.Chatcolor;
import BetterChatColor.Events.Chatevent;
import BetterChatColor.utils.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin{

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("(BetterChatColor) is loading...");
        File config = new File(getDataFolder(),"config.yml");
        if (! (config.exists())) {
            Bukkit.getConsoleSender().sendMessage("Config file not found, Creating one for you!");
        }

        saveDefaultConfig();
        int pluginId = 17488;
        new MetricsLite(this,pluginId);
        new Chatcolor(this);
        new Chatevent(this);
        Bukkit.getConsoleSender().sendMessage("(BetterChatColor) has loaded! Welcome!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
