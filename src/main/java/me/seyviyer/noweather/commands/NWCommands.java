package me.seyviyer.noweather.commands;

import me.seyviyer.noweather.NoWeather;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NWCommands implements CommandExecutor {

    private final NoWeather plugin;
    public NWCommands(NoWeather plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("noweather")) {
            if(sender.hasPermission("noweather.admin")) {
                if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("add")) {
                        if(plugin.getConfigManager().addDisabledWorld(args[1])) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    "&aSuccessfully added &r&6" + args[1] + "&r&a to disabled weather list."));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    "&a" + args[1] + " &r&cis already on the list of disabled world."));
                        }
                    } else if(args[0].equalsIgnoreCase("remove")) {
                        if(plugin.getConfigManager().removeDisabledWorld(args[1])) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    "&aSuccessfully removed &r&6" + args[1] + "&r&a to disabled weather list." ));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    "&a" + args[1] + " &r&cis not on the list of disabled world."));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&6Usage: &r&a/noweather <add|remove> <world>"));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&6Usage: &r&a/noweather <add|remove> <world>"));
                }
            }
            return true;
        }

        return false;
    }
}
