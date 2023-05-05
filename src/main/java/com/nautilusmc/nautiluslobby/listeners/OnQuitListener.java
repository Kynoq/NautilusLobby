package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuitListener implements Listener {
    private final NautilusLobbyMain main;

    public OnQuitListener(NautilusLobbyMain main) {
        this.main = main;
    }
    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "✗" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + player.getName());

    }
}
