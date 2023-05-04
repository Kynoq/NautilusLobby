package com.nautilusmc.nautiluslobby.listeners;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class VoidFallListener implements Listener {

    private final double voidHeight;
    private final double teleportX;
    private final double teleportY;
    private final double teleportZ;
    private final float teleportPitch;
    private final float teleportYaw;


    public VoidFallListener(JavaPlugin main) {
        FileConfiguration config = main.getConfig();
        this.voidHeight = config.getDouble("minimum_y_coordinate");

        this.teleportX = config.getDouble("teleport-location.x");
        this.teleportY = config.getDouble("teleport-location.y");
        this.teleportZ = config.getDouble("teleport-location.z");
        this.teleportPitch = (float) config.getDouble("teleport-location.pitch");
        this.teleportYaw = (float) config.getDouble("teleport-location.yaw");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double playerY = player.getLocation().getY();

        if (playerY < this.voidHeight) {
            Location teleportLocation = new Location(player.getWorld(), this.teleportX, this.teleportY, this.teleportZ, this.teleportYaw, this.teleportPitch);
            player.teleport(teleportLocation);
            player.setVelocity(new Vector(0, 0, 0));
        }
    }
}