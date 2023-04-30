package com.nautilusmc.nautiluslobby.commands;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HealCommand implements CommandExecutor {

    private final NautilusLobbyMain main;

    public HealCommand(NautilusLobbyMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (main.getConfig().getBoolean("player-can-heal")) {
                if (player.hasPermission("nautiluslobby.heal")) {
                    player.sendMessage(Objects.requireNonNull(main.getConfig().getString("healed-message")));
                    player.setHealth(20);
                    player.setFoodLevel(20);
                } else {
                    player.sendMessage(Objects.requireNonNull(main.getConfig().getString("permission-denied")));
                }
            } else {
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("heal-is-disabled")));
            }
        }

        return false;
    }
}
