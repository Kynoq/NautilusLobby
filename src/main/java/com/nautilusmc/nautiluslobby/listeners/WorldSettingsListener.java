package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public class WorldSettingsListener implements Listener {
    private final NautilusLobbyMain main;

    public WorldSettingsListener(NautilusLobbyMain main) {
        this.main = main;
    }

    // Disable Block Break
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!main.getConfig().getBoolean("player-can-break")) {
            if (!player.hasPermission("nautiluslobby.break")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getConfig().getString("cannot-break-block"))));
            }
        }
    }

    // Disable Block Place
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!main.getConfig().getBoolean("player-can-place")) {
            if (!player.hasPermission("nautiluslobby.place")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getConfig().getString("cannot-place-block"))));
            }
        }
    }

    // Disable Item Drop
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!main.getConfig().getBoolean("player-drop-item")) {
            if (!player.hasPermission("nautiluslobby.drop")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getConfig().getString("cannot-drop-item"))));
            }
        }
    }

    // Disable Interactions
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!main.getConfig().getBoolean("player-interact")) {
            if (!player.hasPermission("nautiluslobby.interact")) {
                event.setCancelled(true);
            }
        }
    }

    // Disable damage
    @EventHandler
    public void onPlayerTakeDamage(EntityDamageEvent event) {
        if (!main.getConfig().getBoolean("player-take-damage")) {
            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }

    // Disable Food Decrease
    @EventHandler
    public void onFoodDecrease(FoodLevelChangeEvent event) {
        if (!main.getConfig().getBoolean("food-decrease")) {
            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }

    // Disable spawn mob
    @EventHandler
    public void onMonsterSpawn(CreatureSpawnEvent event) {
        if (!main.getConfig().getBoolean("mobs-spawn")) {
                event.setCancelled(true);
            }
        }

    // Disable weather cycle
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (!main.getConfig().getBoolean("weather-cycle")) {
            event.setCancelled(true);
        }
    }


    public static class CommandBlocker extends JavaPlugin implements Listener {

        @EventHandler
        public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
            String command = event.getMessage().split(" ")[0].toLowerCase(); // Obtient la commande entrée par le joueur

            // Obtient les commandes à bloquer de la configuration
            FileConfiguration config = getConfig();
            List<String> blockedCommands = config.getStringList("blocked-commands");

            // Vérifie si la commande entrée doit être bloquée
            if (blockedCommands.contains(command)) {
                event.setCancelled(true); // Annule l'exécution de la commande

                // Obtient le message personnalisé à partir du fichier de configuration et remplace les codes de couleur
                String blockedCommandMessage = config.getString("blocked-command-message", "Cette commande est désactivée.");
                blockedCommandMessage = ChatColor.translateAlternateColorCodes('&', blockedCommandMessage);

                event.getPlayer().sendMessage(blockedCommandMessage); // Envoie le message d'erreur au joueur
            }
        }
    }
}
