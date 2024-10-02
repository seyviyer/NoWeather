package me.seyviyer.noweather;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class NoWeather extends JavaPlugin implements Listener {

    private List<String> disabledWeatherWorlds = new ArrayList<>();


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        for(String world : getConfig().getStringList("worlds")) {
            disabledWeatherWorlds.add(world);
        }
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {

        if(event.toWeatherState()) {
            for(String disabledWeatherWorld : disabledWeatherWorlds) {
                World world = getServer().getWorld(disabledWeatherWorld);
                if(event.getWorld() == world) {
                    event.setCancelled(true);
                    world.setStorm(false);
                    world.setThundering(false);
                    world.setWeatherDuration(0);
                }
            }
        }


    }
}
