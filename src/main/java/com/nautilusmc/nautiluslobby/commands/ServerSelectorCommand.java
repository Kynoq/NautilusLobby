package com.nautilusmc.nautiluslobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ServerSelectorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // WARNING : If you change this line, also change in MenuListener !
            Inventory inv = Bukkit.createInventory(player, 27, ChatColor.DARK_GRAY + "Connexion à VanillaCraft");

            // Server selector
            ItemStack serverselector = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta meta = serverselector.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Vanilla" + ChatColor.DARK_RED + ChatColor.BOLD + "Craft");
            meta.setLore(Arrays.asList(ChatColor.WHITE + "Survie Vanilla " + ChatColor.DARK_GRAY + "[1.19.4]"));
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            serverselector.setItemMeta(meta);

            inv.setItem(13, serverselector);

            // Frame
            ItemStack frame = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            for (int i : new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,25,26}) {
                inv.setItem(i, frame);
            }


            player.openInventory(inv);



        }


        return false;
    }
}
