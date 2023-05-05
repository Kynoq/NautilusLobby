package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class OnJoinListener implements Listener {

    private final JavaPlugin main;
    private final double teleportX;
    private final double teleportY;
    private final double teleportZ;
    private final float teleportPitch;
    private final float teleportYaw;

    public OnJoinListener(NautilusLobbyMain main) {
        FileConfiguration config = main.getConfig();

        this.main = main;
        this.teleportX = config.getDouble("teleport-location.x");
        this.teleportY = config.getDouble("teleport-location.y");
        this.teleportZ = config.getDouble("teleport-location.z");
        this.teleportPitch = (float) config.getDouble("teleport-location.pitch");
        this.teleportYaw = (float) config.getDouble("teleport-location.yaw");

    }

    // On Join Events
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        FileConfiguration config = main.getConfig();
        boolean forceSpawnOnConnect = config.getBoolean("force-spawn-on-connect");

        if (forceSpawnOnConnect) {
            Location teleportLocation = new Location(player.getWorld(), this.teleportX, this.teleportY, this.teleportZ, this.teleportYaw, this.teleportPitch);
            player.teleport(teleportLocation);
        }

        event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "✔" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + player.getName());


        // Tablist
        List<String> headerLines = config.getStringList("tab.header");
        List<String> footerLines = config.getStringList("tab.footer");

        String header = ChatColor.translateAlternateColorCodes('&', String.join("\n", headerLines));
        String footer = ChatColor.translateAlternateColorCodes('&', String.join("\n", footerLines));

        event.getPlayer().setPlayerListHeaderFooter(header, footer);
    }

    // Setting the default inventory (Clearinv & put default items)
    @EventHandler
    public void onJoinItems(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        player.getInventory().clear();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta metacompass = compass.getItemMeta();


        String menuItemDisplayName = main.getConfig().getString("inventory.compass-display-name");
        menuItemDisplayName = ChatColor.translateAlternateColorCodes('&', menuItemDisplayName);

        String visibilityItemDisplayName = main.getConfig().getString("inventory.visibility-item-display-name");
        visibilityItemDisplayName = ChatColor.translateAlternateColorCodes('&', visibilityItemDisplayName);

        metacompass.setDisplayName(menuItemDisplayName);
        List<String> compassLore = new ArrayList<>();
        for (String compassLoreLine : main.getConfig().getStringList("inventory.compass-lore")) {
            compassLore.add(ChatColor.translateAlternateColorCodes('&', compassLoreLine));
        }
        metacompass.setLore(compassLore);
        compass.setItemMeta(metacompass);

        ItemStack visbilityitem = new ItemStack(Material.LIME_DYE);
        ItemMeta metavisibilityitem = visbilityitem.getItemMeta();

        metavisibilityitem.setDisplayName(visibilityItemDisplayName);
        List<String> visibilityItemLore = new ArrayList<>();
        for (String visibilityItemLoreLine : main.getConfig().getStringList("inventory.visibility-item-lore")) {
            visibilityItemLore.add(ChatColor.translateAlternateColorCodes('&', visibilityItemLoreLine));
        }
        metavisibilityitem.setLore(visibilityItemLore);
        visbilityitem.setItemMeta(metavisibilityitem);

        if (main.getConfig().getBoolean("visibility-item")) { //Because feature is now not intergrated
            player.getInventory().setItem(8, visbilityitem);
        }
        player.getInventory().setItem(4, compass);
    }



}
