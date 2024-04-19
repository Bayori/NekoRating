package net.nekorise.nekorating.Events;

import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.SendRewardInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidTriggerEvent;

import java.util.List;

public class RaidEvents implements Listener {
    @EventHandler
    public void onTriggerEvent(RaidTriggerEvent event)
    {
        if (!RewardConfigData.isRaidTriggerEnabled || !event.getPlayer().hasPermission("nekorating.enableget")) { return; }


        Player player = event.getPlayer();
        MySQL mySQL = new MySQL();
        SendRewardInfo sendRewardInfo = new SendRewardInfo(player, RewardConfigData.raidTriggerReward);

        mySQL.addPoints(player.getName(), RewardConfigData.raidTriggerReward);
        sendRewardInfo.sendRewardMessage();

    }

    @EventHandler
    public void onRaidWin(RaidFinishEvent event)
    {
        if (!RewardConfigData.isRaidWinEnabled) { return; }

        List<Player> winnerPlayers = event.getWinners();
        MySQL mySQL = new MySQL();
        SendRewardInfo sendRewardInfo;

        for (Player winner : winnerPlayers)
        {
            mySQL.addPoints(winner.getName(), RewardConfigData.raidWinReward);
            sendRewardInfo = new SendRewardInfo(winner, RewardConfigData.raidWinReward);
            sendRewardInfo.sendRewardMessage();
        }
    }
}
