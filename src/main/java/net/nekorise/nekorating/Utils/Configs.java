package net.nekorise.nekorating.Utils;

import net.nekorise.nekorating.NekoRating;
import net.nekorise.nekorating.Utils.ConfigStorage.DefaultConfigData;
import net.nekorise.nekorating.Utils.ConfigStorage.LanguageConfigData;
import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configs
{

    private final static String path = "./plugins/NekoRating/";

    // vvv PASTE NEW LOCALIZATION FILENAMES HERE vvv
    private final static String[] languageFiles = { "en.yml", "ru.yml" };

    public static FileConfiguration getConfig(String cfgName) throws IOException, InvalidConfigurationException
    {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.options().parseComments(true);
        fileConfiguration.load(path + cfgName);

        return fileConfiguration;
    }

    public static void loadConfig()
    {
        createConfigs();

        DefaultConfigData.reloadData();
        LanguageConfigData.reloadData();
        RewardConfigData.reloadData();
        MySQL.loadData();
    }

    private static boolean createConfigs()
    {
        File defaultConfig = new File(path, "config.yml");
        if (!defaultConfig.exists())
        {
            NekoRating.getPlugin().saveResource("config.yml", false);
        }

        File rewardConfig = new File(path, "reward.yml");
        if (!rewardConfig.exists())
        {
            NekoRating.getPlugin().saveResource("reward.yml", false);
        }

        List<String> langConfigFiles = new ArrayList<>(Arrays.asList(languageFiles));
        for (String langConfigName : langConfigFiles)
        {
            File langConfig = new File(path, "langs/" + langConfigName);
            if (!langConfig.exists())
            {
                NekoRating.getPlugin().saveResource("langs/" + langConfigName, false);
            }
        }


        return true;
    }
}
