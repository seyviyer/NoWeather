package me.seyviyer.noweather.config;

import com.google.common.io.ByteStreams;
import me.seyviyer.noweather.NoWeather;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class NWConfig {

    private FileConfiguration configuration;

    List<String> disabledWorlds = new ArrayList<>();


    private final NoWeather plugin;
    public NWConfig(NoWeather plugin) {
        this.plugin = plugin;
    }


    public void loadConfiguration() {

        File folder = plugin.getDataFolder();
        if(!folder.exists()) {
            folder.mkdir();
        }

        File resourceFile = new File(folder, "config.yml");
        if(!resourceFile.exists())
            try {
                resourceFile.createNewFile();
                try (InputStream input = plugin.getResource("config.yml")) {
                    OutputStream output = new FileOutputStream(resourceFile);
                    ByteStreams.copy(input, output);
                }
            } catch (Exception e) {
                e.printStackTrace();
                plugin.getServer().getLogger().severe("There is something wrong when making the configuration file!");
            }

        this.configuration = (FileConfiguration) YamlConfiguration.loadConfiguration(resourceFile);

    }

    public FileConfiguration getConfiguration() {
        return this.configuration;
    }

    public List<String> getDisabledWorlds() {
        disabledWorlds.addAll(plugin.getConfig().getStringList("worlds"));
        return disabledWorlds;
    }

    public void addDisabledWorld(String world) {
        disabledWorlds.add(world);
    }

    public void removeDisabledWorld(String world) {
        disabledWorlds.remove(world);
    }

}
