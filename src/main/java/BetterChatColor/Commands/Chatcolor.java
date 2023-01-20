package BetterChatColor.Commands;

import BetterChatColor.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Chatcolor implements CommandExecutor, TabCompleter{
    final Plugin plugin;

    public Chatcolor(Main plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("Chatcolor")).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand("Chatcolor")).setPermission(plugin.getConfig().getString("Permission-to-get-help-with-chatcolor"));
    }

    static void noperm(CommandSender sender) {
        sender.sendMessage("§4§lSorry! You don't have permission to use this!");
    }

    static void helpmessage(CommandSender sender,Plugin plugin) {
        if (sender.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("Permission-to-get-help-with-chatcolor")))) {
            sender.sendMessage("""
                    §a§l-----------------------
                    §e
                    §e§lChatcolor is currently §a§l{nick}§e§l!
                    §e
                    §3§lTo show this page use:
                    §3§l/ChatColor help
                    §2§lTo turn the chat color function on or off use:
                    §3§l/Chatcolor <On|Off>
                    §2§lTo reload our plugin use:
                    §3§l/Chatcolor reload
                    §2§lTo see a book with all available chatcolors
                    §3§l/Chatcolor book
                    §e
                    §a§l-----------------------§r
                    """.replace("{nick}","" + plugin.getConfig().getBoolean("Colorchat")).replace("true","Enabled").replace("false","Disabled"));
        } else {
            noperm(sender);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {

        if (args.length == 0) {
            helpmessage(sender,plugin);
            return true;
        }
        switch (args[0]) {
            case "book","chatcolors" -> {
                if (sender.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("Permission-to-use-chatcolor-book")))) {
                    if (sender instanceof Player player) {
                        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                        BookMeta bookMeta = (BookMeta) book.getItemMeta();
                        Objects.requireNonNull(bookMeta).setAuthor("BetterChatColor");
                        bookMeta.setTitle("§a§lChat color codes!");
                        ArrayList<String> pages = new ArrayList<>();
                        pages.add("""
                                §0§uMinecraft Formatting
                                                                
                                §0&0 §1&1 §2&2 §3&3 §4&4 §5&5 §6&6 §7&7 §8&8 §9&9 §a&a §b&b §c&c §d&d §e&e
                                                                
                                §0k §keeeeeeeee
                                §0l §lMinecraft
                                §0m §mMinecraft
                                §0n §nMinecraft
                                §0o §oMinecraft
                                §0r §rMinecraft""");
                        bookMeta.setPages(pages);
                        book.setItemMeta(bookMeta);
                        player.getInventory().addItem(book);
                        sender.sendMessage("§a§lYou have been given the color book!");
                    }
                }
            }
            case "off","disable" -> {
                if (sender.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("Permission-to-disable-chatcolor")))) {
                    plugin.getConfig().set("Colorchat",false);
                    sender.sendMessage("§e§lColorchat has been §a§lDisabled§e§l.");
                    plugin.saveConfig();
                } else {
                    noperm(sender);
                }
            }
            case "on","enable" -> {
                if (sender.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("Permission-to-enable-chatcolor")))) {
                    plugin.getConfig().set("Colorchat",true);
                    sender.sendMessage("§e§lColorchat has been §a§lEnabled§e§l.");
                    plugin.saveConfig();
                } else {
                    noperm(sender);
                }
            }
            case "reload" -> {
                if (sender.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("Permission-to-reload-chatcolor")))) {
                    plugin.saveConfig();
                    sender.sendMessage("§e§lPlugin reloaded!");
                } else {
                    noperm(sender);
                }
            }
            case "help" -> helpmessage(sender,plugin);
            default -> sender.sendMessage("§4§lError, can not find option, " + args[0] + "!");
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String label,String[] args) {
        if (args.length == 1) {
            ArrayList<String> tab = new ArrayList<>();
            tab.add("book");
            tab.add("help");
            tab.add("reload");
            tab.add("on");
            tab.add("off");
            return tab;
        }
        if (args.length >= 2) {
            return Collections.emptyList();
        }
        return null;
    }

}
