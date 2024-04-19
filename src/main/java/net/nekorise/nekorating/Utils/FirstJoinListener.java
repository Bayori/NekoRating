package net.nekorise.nekorating.Utils;

import net.nekorise.nekorating.NekoRating;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class FirstJoinListener implements Listener {
    @EventHandler
    public void onJoinEvent (PlayerLoginEvent event)
    {
       MySQL mySQL = new MySQL();
       Player player = event.getPlayer();
       mySQL.checkUserAvailable(player.getName(), true);
       NekoRating plugin = NekoRating.getPlugin();
    }
}
