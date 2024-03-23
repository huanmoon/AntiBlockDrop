package net.achievevoid.antiblockdrop.listeners;

import java.util.ArrayList;
import java.util.List;
import net.achievevoid.antiblockdrop.events.PlayerGetItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {
    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        Block block = event.getBlock();
        List<ItemStack> items = new ArrayList<>();
        if (player.getItemInHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            ItemStack tempItem = new ItemStack(block.getType(), 1, block.getData());
            items.add(tempItem);
        }
        else {
            items.addAll(block.getDrops());
        }
        PlayerGetItemEvent playerGetItemEvent = new PlayerGetItemEvent(player, items);
        Bukkit.getPluginManager().callEvent(playerGetItemEvent);
        event.getBlock().setType(Material.AIR);
    }
}
