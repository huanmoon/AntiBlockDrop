package net.achievevoid.antiblockdrop.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.achievevoid.antiblockdrop.listeners.PlayerGetItemListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Claim implements CommandExecutor {
    public Claim() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if (!((List)PlayerGetItemListener.unclaimedItems.get(player.getUniqueId())).isEmpty()) {
                List<ItemStack> tempList = new ArrayList((Collection)PlayerGetItemListener.unclaimedItems.get(player.getUniqueId()));
                ((List)PlayerGetItemListener.unclaimedItems.get(player.getUniqueId())).clear();
                Iterator var7 = tempList.iterator();

                while(var7.hasNext()) {
                    ItemStack itemStack = (ItemStack)var7.next();
                    ((List)PlayerGetItemListener.unclaimedItems.get(player.getUniqueId())).addAll(player.getInventory().addItem(new ItemStack[]{itemStack}).values());
                }
                player.sendMessage(ChatColor.GREEN + "Successfully get " + (tempList.size() - ((List)PlayerGetItemListener.unclaimedItems.get(player.getUniqueId())).size()) + " items! There are still " + ((List)PlayerGetItemListener.unclaimedItems.get(player.getUniqueId())).size() + " items remaining");
                if (((List)PlayerGetItemListener.unclaimedItems.get(player.getUniqueId())).isEmpty()) {
                    PlayerGetItemListener.unclaimedItems.remove(player.getUniqueId());
                }
                return true;
            }
            player.sendMessage(ChatColor.RED + "There's no item to claim!");
        }

        return false;
    }
}
