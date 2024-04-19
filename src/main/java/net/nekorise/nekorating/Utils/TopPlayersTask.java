package net.nekorise.nekorating.Utils;

import net.nekorise.nekorating.NekoRating;
import net.nekorise.nekorating.Utils.ConfigStorage.DefaultConfigData;
import org.bukkit.Bukkit;

public class TopPlayersTask
{
    NekoRating plugin;
    TopPlayers topPlayers = new TopPlayers();

    public TopPlayersTask(NekoRating plugin)
    {
        this.plugin = plugin;
    }

    public void runTask()
    {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, () -> topPlayers.refreshPlayers(), 1, DefaultConfigData.refreshTopDelay * 20);
    }
}
