package net.nekorise.nekorating.Utils.ConfigStorage;

import net.nekorise.nekorating.NekoRating;
import net.nekorise.nekorating.Utils.Configs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class RewardConfigData {
    public static HashMap<EntityType, Integer> mobList = new HashMap<>();

    public static boolean isOtherMobsEnabled = false;
    public static int otherMobsReward = 1;

    public static boolean isTradesEnabled = false;
    public static int tradesReward = 5;

    public static boolean isTotemUseEnabled = false;
    public static int totemUseReward = 15;

    public static boolean isRaidTriggerEnabled = false;
    public static int raidTriggerReward = 15;

    public static boolean isRaidWinEnabled = false;
    public static int raidWinReward = 50;

    public static boolean isEnchantmentEnabled = false;
    public static int enchantmentLvl1Reward = 1;
    public static int enchantmentLvl2Reward = 2;
    public static int enchantmentLvl3Reward = 3;

    public static boolean isAchievementEnabled = false;
    public static int achievementReward = 5;


    public static void reloadData()
    {
        try
        {
            FileConfiguration cfg = Configs.getConfig("reward.yml");

            mobList.clear();
            for (int i = 0; true; i++)
            {
                String entityPath = "entities-kill."+i;
                if (cfg.get(entityPath) == null)
                {
                    break;
                }

                EntityType entityFromCfg = EntityType.valueOf(cfg.getString(entityPath+".entity-type"));
                int rewardFromCfg = cfg.getInt(entityPath+".reward");
                mobList.put(entityFromCfg, rewardFromCfg);
            }

            isOtherMobsEnabled = cfg.getBoolean("other-entities-kill.enable");
            otherMobsReward = cfg.getInt("other-entities-kill.reward");

            isTradesEnabled = cfg.getBoolean("trades.enable");
            tradesReward = cfg.getInt("trades.reward");

            isTotemUseEnabled = cfg.getBoolean("totem-use.enable");
            totemUseReward = cfg.getInt("totem-use.reward");

            isRaidTriggerEnabled = cfg.getBoolean("raid-trigger.enable");
            raidTriggerReward = cfg.getInt("raid-trigger.reward");

            isRaidWinEnabled = cfg.getBoolean("raid-win.enable");
            raidWinReward = cfg.getInt("raid-win.reward");

            isEnchantmentEnabled = cfg.getBoolean("enchantment-item.enable");
            enchantmentLvl1Reward = cfg.getInt("enchantment-item.lvl1-reward");
            enchantmentLvl2Reward = cfg.getInt("enchantment-item.lvl2-reward");
            enchantmentLvl3Reward = cfg.getInt("enchantment-item.lvl3-reward");

            isAchievementEnabled = cfg.getBoolean("achievement.enable");
            achievementReward = cfg.getInt("achievement.reward");
        }
        catch (Exception ex)
        {
            NekoRating.getPlugin().getLogger().warning("reward.yml is invalid");
            ex.printStackTrace();
        }

    }
}
