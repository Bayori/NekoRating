package net.nekorise.nekorating.Utils.ConfigStorage;

import net.nekorise.nekorating.Utils.Configs;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class DefaultConfigData {

    public static String sqlSchema = "nekorating";
    public static String sqlTable = "players";
    public static String sqlUser = "root";
    public static String sqlPassword = "";
    public static String sqlServer = "localhost";
    public static String sqlPort = "3306";
    public static int startPoints = 0;
    public static boolean notifyAdd = true;
    public static boolean notifyTake = true;
    public static boolean notifySet = true;
    public static String notifyReward = "ACTION_BAR";
    public static boolean isNotifyRewardSoundEnabled = false;
    public static Sound notifyRewardSoundId = Sound.BLOCK_AMETHYST_BLOCK_STEP;
    public static String lang = "ru";
    public static long refreshTopDelay = 10;

    public static void reloadData()
    {
        try
        {
            FileConfiguration cfg = Configs.getConfig("config.yml");

            sqlSchema = cfg.getString("MySQL.database-name");
            sqlTable = cfg.getString("MySQL.table-name");
            sqlUser = cfg.getString("MySQL.login");
            sqlPassword = cfg.getString("MySQL.password");
            sqlServer = cfg.getString("MySQL.server");
            sqlPort = cfg.getString("MySQL.port");
            startPoints = cfg.getInt("points.start-points");
            notifyAdd = cfg.getBoolean("players.notify-player-add-command");
            notifyTake = cfg.getBoolean("players.notify-player-take-command");
            notifySet = cfg.getBoolean("players.notify-player-set-command");
            notifyReward = cfg.getString("players.notify-player-reward");
            isNotifyRewardSoundEnabled = cfg.getBoolean("players.notify-player-reward-sound.enabled");
            notifyRewardSoundId = Sound.valueOf(cfg.getString("players.notify-player-reward-sound.sound"));
            lang = cfg.getString("language");
            refreshTopDelay = cfg.getInt("tops.refresh-top-global");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
