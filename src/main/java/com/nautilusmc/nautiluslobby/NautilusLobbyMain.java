package com.nautilusmc.nautiluslobby;

import com.nautilusmc.nautiluslobby.commands.HealCommand;
import com.nautilusmc.nautiluslobby.commands.ServerSelectorCommand;
import com.nautilusmc.nautiluslobby.commands.SetLobbySpawnCommand;
import com.nautilusmc.nautiluslobby.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NautilusLobbyMain extends JavaPlugin {

        @Override
        public void onEnable() {


        // Register config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Register listeners
        Bukkit.getPluginManager().registerEvents(new WorldSettingsListener(this), this);
        Bukkit.getPluginManager().registerEvents(new OnQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new OnJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ServerSelectorListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);
        Bukkit.getPluginManager().registerEvents(new VoidFallListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CommandBlocker(this), this);

        // Commands register
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("server").setExecutor(new ServerSelectorCommand(this));
        getCommand("setlobbyspawn").setExecutor(new SetLobbySpawnCommand(this));

        getLogger().info("NautilusLobby started successfully !");
    }

    @Override
    public void onDisable() {
        // Configuration Backup

        getLogger().info("NautilusLobby disabled!");
    }

}
