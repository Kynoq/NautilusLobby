package com.nautilusmc.nautiluslobby.commands;

import com.nautilusmc.nautiluslobby.NautilusLobbyMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ServerSelectorCommand implements CommandExecutor {

    private final NautilusLobbyMain main;

    public ServerSelectorCommand(NautilusLobbyMain main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            String menuTitle = main.getConfig().getString("menu.title");
            menuTitle = ChatColor.translateAlternateColorCodes('&', menuTitle);
            Inventory inv = Bukkit.createInventory(player, 27, menuTitle);

            String menuItemDisplayName = main.getConfig().getString("inventory.compass-display-name");
            menuItemDisplayName = ChatColor.translateAlternateColorCodes('&', menuItemDisplayName);

            String visibilityItemDisplayName = main.getConfig().getString("inventory.visibility-item-display-name");
            visibilityItemDisplayName = ChatColor.translateAlternateColorCodes('&', visibilityItemDisplayName);

            String serverSelectorItem = main.getConfig().getString("menu.server-selector-item");
            serverSelectorItem = ChatColor.translateAlternateColorCodes('&', serverSelectorItem);

            // Server selector
            String serverSelectorMaterial = main.getConfig().getString("menu.server-connect-item");
            Material serverSelectorMat = Material.getMaterial(serverSelectorMaterial);

            ItemStack serverselector = new ItemStack(serverSelectorMat);
            ItemMeta meta = serverselector.getItemMeta();

            // Load lore from config.yml
            List<String> loreList = main.getConfig().getStringList("menu.server-selector-lore");
            List<String> translatedLoreList = new ArrayList<>();
            for (String loreLine : loreList) {
                translatedLoreList.add(ChatColor.translateAlternateColorCodes('&', loreLine));
            }
            meta.setLore(translatedLoreList);

            meta.setDisplayName(serverSelectorItem);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

            serverselector.setItemMeta(meta);
            inv.setItem(13, serverselector);

            // Frame
            String fillItemMaterial = main.getConfig().getString("menu.fill-item");
            Material fillItemMat = Material.getMaterial(fillItemMaterial);

            ItemStack frame = new ItemStack(fillItemMat);
            for (int i : new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,25,26}) {
                inv.setItem(i, frame);
            }


            player.openInventory(inv);



        }


        return false;
    }

    @EventHandler
    public void onClickInteract(InventoryClickEvent e) {

        String menuItemDisplayName = main.getConfig().getString("menu.menu-item-display-name");
        menuItemDisplayName = ChatColor.translateAlternateColorCodes('&', menuItemDisplayName);

        String visibilityItemDisplayName = main.getConfig().getString("menu.visibility-item-display-name");
        visibilityItemDisplayName = ChatColor.translateAlternateColorCodes('&', visibilityItemDisplayName);

        if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(menuItemDisplayName) || e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(visibilityItemDisplayName)) {
            e.setCancelled(true);

        }

    }
}
