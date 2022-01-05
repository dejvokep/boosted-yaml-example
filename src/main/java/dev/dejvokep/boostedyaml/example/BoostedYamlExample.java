package dev.dejvokep.boostedyaml.example;

import dev.dejvokep.boostedyaml.YamlFile;
import dev.dejvokep.boostedyaml.fvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class BoostedYamlExample extends JavaPlugin implements Listener {

    // Config
    private YamlFile file;

    @Override
    public void onEnable() {
        // Register listener
        Bukkit.getPluginManager().registerEvents(this, this);

        // Create and update the file
        try {
            file = YamlFile.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.DEFAULT, DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
            file.update();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', file.getString("join-message")));
    }
}
