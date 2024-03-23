package net.achievevoid.antiblockdrop.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.achievevoid.antiblockdrop.AntiBlockDrop;
import net.achievevoid.antiblockdrop.events.PlayerGetItemEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerGetItemListener implements Listener {
    public static HashMap unclaimedItems = new HashMap();

    @EventHandler
    private void onGetItem(PlayerGetItemEvent event) {
        List<ItemStack> items = event.getItems();
        if (items.isEmpty()) {
            event.setCancelled(true);
        }
        else {
            Player player = event.getPlayer();
            List<ItemStack> remainingItems = new ArrayList<>();
            Iterator<ItemStack> var5 = items.iterator();
            ItemStack itemStack;
            while(var5.hasNext()) {
                itemStack = var5.next();
                remainingItems.addAll(player.getInventory().addItem(new ItemStack[]{itemStack}).values());
            }

            if (!remainingItems.isEmpty()) {
                if (AntiBlockDrop.cliamEnabled) {
                    ((List)unclaimedItems.get(player.getUniqueId())).addAll(remainingItems);
                    player.sendMessage(ChatColor.RED + "Oops! Your inventory is full and there are " + ((List<?>)unclaimedItems.get(player.getUniqueId())).size() + " items remaining! Use /claim to claim them.");
                } else {
                    var5 = remainingItems.iterator();

                    while(var5.hasNext()) {
                        itemStack = var5.next();
                        player.getWorld().dropItem(player.getLocation(), itemStack);
                    }
                }
            }
        }

    }
}
