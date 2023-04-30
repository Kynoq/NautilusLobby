package com.nautilusmc.nautiluslobby;

import com.nautilusmc.nautiluslobby.commands.HealCommand;
import com.nautilusmc.nautiluslobby.commands.ServerSelectorCommand;
import com.nautilusmc.nautiluslobby.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NautilusLobbyMain extends JavaPlugin {

    @Override
    public void onEnable() {

        // Register listeners
        Bukkit.getPluginManager().registerEvents(new WorldSettingsListener(this), this);
        Bukkit.getPluginManager().registerEvents(new OnQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new OnJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ServerSelectorListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);

        // Register config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // On start print
        System.out.println("NautilusLobby Started!");

        // Commands register
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("server").setExecutor(new ServerSelectorCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
