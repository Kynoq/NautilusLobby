package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ServerSelectorListener implements Listener {

    private final NautilusLobbyMain main;

    public ServerSelectorListener(NautilusLobbyMain main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null) {
            return;
        }

        switch (e.getRawSlot()) {
            case 13: // Execute command
                player.chat(Objects.requireNonNull(main.getConfig().getString("menu.server-selector-command")));
                break;
            default:
                e.setCancelled(true);
                break;
        }

        String serverSelectorItem = main.getConfig().getString("menu.server-selector-item");
        serverSelectorItem = ChatColor.translateAlternateColorCodes('&', serverSelectorItem);

        if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasDisplayName() &&
                clickedItem.getItemMeta().getDisplayName().equals(serverSelectorItem)) {

            e.setCancelled(true);
            player.closeInventory();

        }


    }


}
