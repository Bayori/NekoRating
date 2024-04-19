package net.nekorise.nekorating.Events;

import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.SendRewardInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementEvent implements Listener
{
    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event)
    {
        if (!RewardConfigData.isAchievementEnabled || !event.getPlayer().hasPermission("nekorating.enableget")) { return; }

        if (!event.getAdvancement().getKey().getKey().contains("recipes"))
        {
            int rewardAchievement = RewardConfigData.achievementReward;
            MySQL mySQL = new MySQL();
            Player player = event.getPlayer();
            SendRewardInfo sendRewardInfo = new SendRewardInfo(player, rewardAchievement);

            mySQL.addPoints(player.getName(), rewardAchievement);
            sendRewardInfo.sendRewardMessage();
        }
    }
}
