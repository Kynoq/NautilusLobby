package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class ServerSelectorListener implements Listener {

    private final NautilusLobbyMain main;

    public ServerSelectorListener(NautilusLobbyMain main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        // WARNING : If you change this line, also change in MenuCommand !
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.DARK_GRAY + "Connexion à VanillaCraft")
                && e.getCurrentItem() != null) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            switch (e.getRawSlot()) {
                case 13: // Execute command
                    player.chat(Objects.requireNonNull(main.getConfig().getString("pickaxe-command")));
                    break;

                default:
                    return;
            }
            player.closeInventory();

        }


    }


}
