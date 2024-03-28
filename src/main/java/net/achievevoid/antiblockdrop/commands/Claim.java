package net.achievevoid.antiblockdrop.commands;

import net.achievevoid.antiblockdrop.listeners.PlayerGetItemListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Claim implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(!PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()).isEmpty()) {
                List<ItemStack> tempList = new ArrayList<>(PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()));
                PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()).clear();
                for(ItemStack itemStack : tempList) {
                    PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()).addAll(player.getInventory().addItem(itemStack).values());
                }
                player.sendMessage(ChatColor.GREEN + "Successfully get " + (tempList.size() - PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()).size()) + " items! There are still " + PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()).size() + " items remaining");
                if(PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()).isEmpty()) {
                    PlayerGetItemListener.unclaimedItems.remove(player.getUniqueId());
                }
                return true;
            }
            player.sendMessage(ChatColor.RED + "There's no item to claim!");
        }

        return false;
    }
}
