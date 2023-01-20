package BetterChatColor.Events;

import BetterChatColor.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class Chatevent implements Listener{

    final Plugin plugin;

    public Chatevent(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("Permission-to-use-chatcolor"))) && plugin.getConfig().getBoolean("Colorchat")) {
            event.setMessage(event.getMessage().replace('&','§'));
        }
    }
}
