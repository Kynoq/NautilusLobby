package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandBlocker implements Listener {

    private final NautilusLobbyMain main;

    public CommandBlocker(NautilusLobbyMain main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0].toLowerCase();

        FileConfiguration config = main.getConfig();
        List<String> blockedCommands = config.getStringList("blocked-commands");

        if (blockedCommands.contains(command)) {
            event.setCancelled(true);


            String blockedCommandMessage = config.getString("blocked-command-message");
            blockedCommandMessage = ChatColor.translateAlternateColorCodes('&', blockedCommandMessage);

            event.getPlayer().sendMessage(blockedCommandMessage);
        }
    }

}
