package net.achievevoid.antiblockdrop.listeners;

import java.util.*;

import net.achievevoid.antiblockdrop.AntiBlockDrop;
import net.achievevoid.antiblockdrop.events.PlayerGetItemEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerGetItemListener implements Listener {
    public static HashMap<UUID, List<ItemStack>> unclaimedItems = new HashMap<>();

    @EventHandler
    private void onGetItem(PlayerGetItemEvent event) {
        List<ItemStack> items = event.getItems();
        if (items.isEmpty()) {
            event.setCancelled(true);
        }
        else {
            Player player = event.getPlayer();
            List<ItemStack> remainingItems = new ArrayList<>();
            for(ItemStack itemStack : items) {
                remainingItems.addAll(player.getInventory().addItem(itemStack).values());
            }
            if (!remainingItems.isEmpty()) {
                if (AntiBlockDrop.cliamEnabled) {
                    unclaimedItems.get(player.getUniqueId()).addAll(remainingItems);
                    player.sendMessage(ChatColor.RED + "Oops! Your inventory is full and there are " + unclaimedItems.get(player.getUniqueId()).size() + " items remaining! Use /claim to claim them.");
                }
                else {
                    for(ItemStack itemStack : items) {
                        player.getWorld().dropItem(player.getLocation(), itemStack);
                    }
                }
            }
        }

    }
}
