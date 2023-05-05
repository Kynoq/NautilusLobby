package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryListener implements Listener {

    private final NautilusLobbyMain main;

    public InventoryListener(NautilusLobbyMain main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();

        String menuItemDisplayName = main.getConfig().getString("inventory.compass-display-name");
        menuItemDisplayName = ChatColor.translateAlternateColorCodes('&', menuItemDisplayName);

        String visibilityItemDisplayName = main.getConfig().getString("inventory.visibility-item-display-name");
        visibilityItemDisplayName = ChatColor.translateAlternateColorCodes('&', visibilityItemDisplayName);

        if (e.getAction().name().contains("RIGHT") &&
                e.getItem() != null &&
                e.getItem().getType() == Material.COMPASS &&
                e.getItem().getItemMeta().getDisplayName().equals(menuItemDisplayName)) {

            e.setCancelled(true);
            player.chat("/server");

        } else if (e.getAction().name().contains("RIGHT") &&
                e.getItem() != null &&
                e.getItem().getType() == Material.LIME_DYE &&
                e.getItem().getItemMeta().getDisplayName().equals(visibilityItemDisplayName)) {

            e.setCancelled(true);

            player.sendMessage("Fonction en développement");
        }

    }



}
