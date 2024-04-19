package net.nekorise.nekorating.Utils.ConfigStorage;

import net.nekorise.nekorating.NekoRating;
import net.nekorise.nekorating.Utils.Configs;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class LanguageConfigData
{
    public static String cfgReload = "&#e0aa1dConfigs and database connection reloaded";
    // public static String cfgReloadFail = "cfg is invalid";
    public static String rpointsUsage = "&#e04c1dUsage: &#e0b01d/rpoints <add/take/set> <Nickname> <Amount>";
    public static String rpointsError = "&#e04c1dCould not find such a player in the database";
    public static String rpointsAddSender = "&#e0aa1dThe player &#1de074{player} &#e0aa1dhas been added &#1de074{points} &#e0aa1dpoints";
    public static String rpointsAddPlayer = "&#e0aa1dYou have been given &#1de074{points} &#e0aa1drating points";
    public static String rpointsTakeSender = "&#e0aa1dThe player &#1de074{player} &#e0aa1dhas been taked &#1de074{points} &#e0aa1dpoints";
    public static String rpointsTakePlayer = "&#e0aa1dYou had &#1de074{points} &#e0aa1drating points taken away from you";
    public static String rpointsSetSender = "&#e0aa1dThe player now has &#1de074{player} &#1de074{points} &#e0aa1dpoints";
    public static String rpointsSetPlayer = "&#e0aa1dYou have been assigned &#1de074{points} &#e0aa1drating points";

    public static String rewardActionbarMessage = "&#e0aa1dYou've earned &#1de074{points} &#e0aa1dpoints";
    public static String rewardChatMessage = "&#e0aa1dYou've earned &#1de074{points} &#e0aa1dpoints";
    public static String rewardTitleMessage = "&#e0aa1dYay!";
    public static String rewardSubtitleMessage = "&#e0aa1dYou've earned &#1de074{points} &#e0aa1dpoints";

    public static String papiZeroTopUsername = "Empty";
    public static String papiZeroTopPoints = "0";
    public static String papiZeroPlayerTop = "-1";

    public static String profileGuiName = "Rating system";

    public static Material profileMaterial = Material.CONDUIT;
    public static String profileName = "Your Profile";
    public static List<String> profileLore = List.of("Name: user","Points: -1");

    public static Material top1Material = Material.NETHERITE_BLOCK;

    public static Material top2Material = Material.DIAMOND_BLOCK;

    public static Material top3Material = Material.GOLD_BLOCK;

    public static Material topOtherMaterial = Material.COPPER_BLOCK;
    public static String topOtherName = "#{top_place}";
    public static List<String> topOtherLore = List.of("Points: -1");

    public static Material toEventListMaterial = Material.MOJANG_BANNER_PATTERN;
    public static String toEventListName = "How to get points?";
    public static List<String> toEventListLore = List.of("Click (pls)");

    public static void reloadData()
    {
        try
        {
            FileConfiguration cfg = Configs.getConfig("langs/" + DefaultConfigData.lang + ".yml");

            cfgReload = cfg.getString("configuration-reload");
            // cfgReloadFail = cfg.getString("configuration-reload-fail");

            rpointsUsage = cfg.getString("rpoints.usage");
            rpointsError = cfg.getString("rpoints.error");
            rpointsAddSender = cfg.getString("rpoints.add-sender");
            rpointsAddPlayer = cfg.getString("rpoints.add-player");
            rpointsTakeSender = cfg.getString("rpoints.take-sender");
            rpointsTakePlayer = cfg.getString("rpoints.take-player");
            rpointsSetSender = cfg.getString("rpoints.set-sender");
            rpointsSetPlayer = cfg.getString("rpoints.set-player");

            rewardActionbarMessage = cfg.getString("reward-messages.ACTION_BAR");
            rewardChatMessage = cfg.getString("reward-messages.CHAT_MESSAGE");
            rewardTitleMessage = cfg.getString("reward-messages.TITLE.title");
            rewardSubtitleMessage = cfg.getString("reward-messages.TITLE.subtitle");

            papiZeroTopUsername = cfg.getString("papi.zero-value-top-username");
            papiZeroTopPoints = cfg.getString("papi.zero-value-top-points");
            papiZeroPlayerTop = cfg.getString("papi.zero-value-player-top");

            profileGuiName = cfg.getString("gui.profile-menu.name");

            profileMaterial = Material.valueOf(cfg.getString("gui.profile-menu.profile-item.material"));
            top1Material = Material.valueOf(cfg.getString("gui.profile-menu.top-1-item.material"));
            top2Material = Material.valueOf(cfg.getString("gui.profile-menu.top-2-item.material"));
            top3Material = Material.valueOf(cfg.getString("gui.profile-menu.top-3-item.material"));
            topOtherMaterial = Material.valueOf(cfg.getString("gui.profile-menu.top-4-15-item.material"));
            //toEventListMaterial = Material.valueOf(cfg.getString("gui.profile-menu.to-event-list-item.material"));

            profileName = cfg.getString("gui.profile-menu.profile-item.name");
            topOtherName = cfg.getString("gui.profile-menu.top-items.name");
            //toEventListName = cfg.getString("gui.profile-menu.to-event-list-item.name");

            profileLore = cfg.getStringList("gui.profile-menu.profile-item.lore");
            topOtherLore = cfg.getStringList("gui.profile-menu.top-items.lore");
            //toEventListLore = cfg.getStringList("gui.profile-menu.to-event-list-item.lore");



        }
        catch (Exception e)
        {
            NekoRating.getPlugin().getLogger().warning("Localization file \"" + DefaultConfigData.lang + ".yml\" was not found.");
            e.printStackTrace();
        }
    }
}
