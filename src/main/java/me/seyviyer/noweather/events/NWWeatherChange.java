package me.seyviyer.noweather.events;

import me.seyviyer.noweather.NoWeather;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;


public class NWWeatherChange implements Listener {


    private final NoWeather plugin;
    public NWWeatherChange(NoWeather plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {

        if(event.toWeatherState()) {
            for(String disabledWeatherWorld : plugin.getConfigManager().getDisabledWorlds()) {
                World world = plugin.getServer().getWorld(disabledWeatherWorld);
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
