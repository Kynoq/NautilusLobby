package com.nautilusmc.nautiluslobby.commands;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SetLobbySpawnCommand implements CommandExecutor {



    private final NautilusLobbyMain main;

    public SetLobbySpawnCommand(NautilusLobbyMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by one player!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("nautiluslobby.setlobbyspawn")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getConfig().getString("permission-denied"))));
            return true;
        }

        Location location = player.getLocation();
        FileConfiguration config = main.getConfig();

        config.set("teleport-location.x", location.getX());
        config.set("teleport-location.y", location.getY());
        config.set("teleport-location.z", location.getZ());
        config.set("teleport-location.yaw", location.getYaw());
        config.set("teleport-location.pitch", location.getPitch());

        try {
            config.save(new File(main.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getConfig().getString("lobby-spawn-set"))));
        return true;
    }
}
