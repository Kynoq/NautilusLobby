package com.nautilusmc.nautiluslobby.commands;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        // Vérifiez que le joueur a la permission d'utiliser cette commande
        if (!player.hasPermission("nautiluslobby.setlobbyspawn")) {
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("permission-denied")));
            return true;
        }

        // Obtenez les coordonnées du joueur
        Location location = player.getLocation();

        // Enregistrez les coordonnées dans la configuration
        main.getConfig().set("teleport-location.x", location.getX());
        main.getConfig().set("teleport-location.y", location.getY());
        main.getConfig().set("teleport-location.z", location.getZ());
        main.getConfig().set("teleport-location.yaw", location.getYaw());
        main.getConfig().set("teleport-location.pitch", location.getPitch());

        // Enregistrez les modifications dans le fichier de configuration
        main.saveConfig();

        player.sendMessage(Objects.requireNonNull(main.getConfig().getString("lobby-spawn-set")));
        return true;
    }

}
