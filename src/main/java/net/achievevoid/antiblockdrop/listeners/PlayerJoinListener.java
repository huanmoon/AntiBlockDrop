package net.achievevoid.antiblockdrop.listeners;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!PlayerGetItemListener.unclaimedItems.containsKey(player.getUniqueId())) {
            PlayerGetItemListener.unclaimedItems.put(player.getUniqueId(), new ArrayList());
        }
    }
}
