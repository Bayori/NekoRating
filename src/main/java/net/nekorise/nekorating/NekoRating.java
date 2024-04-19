package net.nekorise.nekorating;

import net.nekorise.nekorating.Commands.*;
import net.nekorise.nekorating.Events.*;
import net.nekorise.nekorating.GUIs.EventListGUIListener;
import net.nekorise.nekorating.GUIs.ProfileGUIListener;
import net.nekorise.nekorating.Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NekoRating extends JavaPlugin
{

    private static NekoRating plugin;
    @Override
    public void onEnable()
    {
        plugin = this;

        Configs.loadConfig();
        MySQL.startupLogic();

        registerCommands();
        registerTabCompleters();
        registerEvents();
        registerPapi();
        runTasks();
    }

    private void registerCommands()
    {
        getCommand("nekorating").setExecutor(new AboutCommand());
        getCommand("rpoints").setExecutor(new RpointsCommand());
        getCommand("profile").setExecutor(new ProfileCommand());
    }

    private void registerTabCompleters()
    {
        getCommand("rpoints").setTabCompleter(new RpointsTabCompleter());
        getCommand("nekorating").setTabCompleter(new AboutTabCompleter());
        getCommand("profile").setTabCompleter(new ProfileTabCompleter());
    }

    private void registerEvents()
    {
        Bukkit.getPluginManager().registerEvents(new ProfileGUIListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new EventListGUIListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new FirstJoinListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new MobKillEvents(), plugin);
        Bukkit.getPluginManager().registerEvents(new TradeEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new TotemUseEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new RaidEvents(), plugin);
        Bukkit.getPluginManager().registerEvents(new EnchantmentEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new AdvancementEvent(), plugin);
    }
    private void runTasks()
    {
        TopPlayersTask topPlayersTask = new TopPlayersTask(getPlugin());
        topPlayersTask.runTask();
    }
    private void registerPapi()
    {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null)
        {
            getLogger().warning("PlaceholderAPI not found. Placeholders will not be enabled.");
            return;
        }
        HookPlaceholderAPI.registerHook();
    }

    public static NekoRating getPlugin()
    {
        return plugin;
    }
}
