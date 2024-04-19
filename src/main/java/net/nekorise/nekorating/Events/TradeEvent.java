package net.nekorise.nekorating.Events;

import io.papermc.paper.event.player.PlayerTradeEvent;
import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.SendRewardInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TradeEvent implements Listener
{
    @EventHandler
    public void onTradeEvent(PlayerTradeEvent event)
    {
        if (!RewardConfigData.isTradesEnabled || !event.getPlayer().hasPermission("nekorating.enableget")) { return; }

        Player player = event.getPlayer();
        MySQL mySQL = new MySQL();
        SendRewardInfo sendRewardInfo = new SendRewardInfo(player, RewardConfigData.tradesReward);

        sendRewardInfo.sendRewardMessage();
        mySQL.addPoints(player.getName(), RewardConfigData.tradesReward);
    }
}
