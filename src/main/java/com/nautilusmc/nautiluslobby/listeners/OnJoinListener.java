package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class OnJoinListener implements Listener {


    public OnJoinListener(NautilusLobbyMain nautilusLobbyMain) {

    }


    // On Join message
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "✔" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + player.getName());
    }

    // Setting the default inventory (Clearinv & put default items)
    @EventHandler
    public void onJoinItems(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        player.getInventory().clear();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta metacompass = compass.getItemMeta();
        // WARNING : If you change this line, also change in InventoryListener !
        metacompass.setDisplayName(ChatColor.YELLOW + "Connexion au serveur " + ChatColor.GRAY + "(Clique droit)");
        metacompass.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Clique Droit pour ouvrir le menu de connexion"));
        compass.setItemMeta(metacompass);

        ItemStack visbilityitem = new ItemStack(Material.LIME_DYE);
        ItemMeta metavisibilityitem = visbilityitem.getItemMeta();
        // WARNING : If you change this line, also change in InventoryListener !
        metavisibilityitem.setDisplayName(ChatColor.YELLOW + "Visibilité des joueurs " + ChatColor.GRAY + ChatColor.BOLD + "> " + ChatColor.GREEN + "Visible");
        metavisibilityitem.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Clique Droit pour masquer les autres joueurs"));
        visbilityitem.setItemMeta(metavisibilityitem);

        player.getInventory().setItem(8, visbilityitem);
        player.getInventory().setItem(4, compass);
    }


}
