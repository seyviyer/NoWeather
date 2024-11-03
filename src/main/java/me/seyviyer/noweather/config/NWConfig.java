package me.seyviyer.noweather.config;

import com.google.common.io.ByteStreams;
import me.seyviyer.noweather.NoWeather;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NWConfig {

    private File configFile;
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

        this.configFile = resourceFile;
        this.configuration = YamlConfiguration.loadConfiguration(configFile);

    }

    public FileConfiguration getConfiguration() {
        return this.configuration;
    }

    public void saveConfiguration() {
        try {
            configuration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().severe("There is a problem saving the configuration file!");
        }
    }

    public List<String> getDisabledWorlds() {

        for(String world : getConfiguration().getStringList("worlds")) {
            if(!disabledWorlds.contains(world)) {
                disabledWorlds.add(world);
            }
        }
    //    disabledWorlds.addAll(getConfiguration().getStringList("worlds"));
        return disabledWorlds;
    }

    /*
        Returns true if adding the world to the list is successful.
        Returns false if it already exists and will not add.
     */
    public boolean addDisabledWorld(String world) {
        if(!getDisabledWorlds().contains(world)) {
            getDisabledWorlds().add(world);
            getConfiguration().set("worlds", getDisabledWorlds());
            saveConfiguration();
            return true;
        } else {
            return false;
        }
    }

    /*
        Returns true if remove the world to the list is successful.
        Returns false if it doesn't exist and will not remove.
     */
    public boolean removeDisabledWorld(String world) {
        if(getDisabledWorlds().contains(world)) {
            getDisabledWorlds().remove(world);
            getConfiguration().set("worlds", getDisabledWorlds());
            saveConfiguration();
            return true;
        } else {
            return false;
        }
    }

}
