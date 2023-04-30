package com.nautilusmc.nautiluslobby.listeners;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

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
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("cannot-break-block")));
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
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("cannot-place-block")));
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
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("cannot-drop-item")));
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


}
