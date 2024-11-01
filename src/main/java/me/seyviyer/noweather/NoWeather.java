package me.seyviyer.noweather;

import me.seyviyer.noweather.config.NWConfig;
import me.seyviyer.noweather.events.NWWeatherChange;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class NoWeather extends JavaPlugin implements Listener {

    private final NWConfig configManager = new NWConfig(this);
    public NWConfig getConfigManager() {
        return configManager;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new NWWeatherChange(this), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

    }

}
