package me.seyviyer.noweather.commands;

import me.seyviyer.noweather.NoWeather;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class NWTabCompleter implements TabCompleter {

    List<String> options = new ArrayList<>();
    private final NoWeather plugin;
    public NWTabCompleter(NoWeather plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("noweather.admin")) {
            if(args.length == 1) {
                return getOptions();
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("remove")) {
                    return plugin.getConfigManager().getDisabledWorlds();
                }
            }
        }

        return List.of();
    }

    public List<String> getOptions() {
        if(options.isEmpty()) {
            options.add("add");
            options.add("remove");
        }

        return options;
    }
}
