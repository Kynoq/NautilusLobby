package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryListener implements Listener {

    private final NautilusLobbyMain main;

    public InventoryListener(NautilusLobbyMain main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();

        // WARNING : If you change this line, also change in OnJoinListener !
        if (e.getAction().name().contains("RIGHT") && e.getItem() != null && e.getItem().getType() == Material.COMPASS && e.getItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Connexion au serveur " + ChatColor.GRAY + "(Clique droit)")) {

            e.setCancelled(true);

            player.chat("/server");

        // WARNING : If you change this line, also change in OnJoinListener !
        } else if (e.getAction().name().contains("RIGHT") && e.getItem() != null && e.getItem().getType() == Material.LIME_DYE && e.getItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Visibilité des joueurs " + ChatColor.GRAY + ChatColor.BOLD + "> " + ChatColor.GREEN + "Visible")) {

            e.setCancelled(true);

            player.sendMessage("Fonction en développement");
        }

    }

    @EventHandler
    public void onClickInteract(InventoryClickEvent e) {

        // WARNING : If you change this line, also change in OnJoinListener !
        if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Visibilité des joueurs " + ChatColor.GRAY + ChatColor.BOLD + "> " + ChatColor.GREEN + "Visible") || e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Connexion au serveur " + ChatColor.GRAY + "(Clique droit)")) {
            e.setCancelled(true);

        }

    }



}
